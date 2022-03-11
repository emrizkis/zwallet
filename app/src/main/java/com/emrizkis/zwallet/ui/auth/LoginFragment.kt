package com.emrizkis.zwallet.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentLoginBinding
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.User
import com.emrizkis.zwallet.model.request.LoginRequest
import com.emrizkis.zwallet.network.NetworkConfig
import com.emrizkis.zwallet.ui.home.MainActivity
import com.emrizkis.zwallet.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        prefs = activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!


        binding.inputPassword.addTextChangedListener {
            if(binding.inputPassword.text.length > 8){
                binding.btnLogin.setBackgroundResource(R.drawable.background_button_auth_active)
                binding.btnLogin.setTextColor(Color.parseColor("#FFFFFF"))
            } else if (binding.inputPassword.text.length <= 8 ){
                binding.btnLogin.setBackgroundResource(R.drawable.background_button_auth)
                binding.btnLogin.setTextColor(Color.parseColor("#9DA6B5"))
            }
        }

        binding.btnLogin.setOnClickListener{
            if(binding.inputEmail.text.isNullOrEmpty() || binding.inputPassword.text.isNullOrEmpty()){
                Toast.makeText(activity, "email/password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val loginRequest = LoginRequest(
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()
            )

            NetworkConfig(context).getService().login(loginRequest).enqueue(object:
                Callback<APIResponse<User>> {
                override fun onResponse(
                    call: Call<APIResponse<User>>,
                    response: Response<APIResponse<User>>
                ) {
                    if (response.body()?.status != HttpURLConnection.HTTP_OK){
                        Toast.makeText(
                            context,
                            "Authentication failed: Wrong email/password",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        val res = response.body()!!.data

                        with(prefs.edit()){
                            putBoolean(KEY_LOGGED_IN, true)
                            putString(KEY_USER_EMAIL, res?.email)
                            putString(KEY_USER_TOKEN, res?.token)
                            putString(KEY_USER_REFRESH_TOKEN, res?.refreshToken )
                            apply()
                        }

                        Handler().postDelayed({
                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        }, 2000)

                    }
                }

                override fun onFailure(call: Call<APIResponse<User>>, t: Throwable) {
                    Toast.makeText(
                        context,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })



        }

        binding.textSignUp.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.loginActionRegister)
        }

        binding.textForgotPassword.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

    }


}