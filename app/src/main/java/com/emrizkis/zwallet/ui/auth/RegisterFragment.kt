package com.emrizkis.zwallet.ui.auth

import android.graphics.Color
import android.net.Network
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentRegisterBinding
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.request.RegisterRequest
import com.emrizkis.zwallet.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        binding.inputPassword.addTextChangedListener {
            if(binding.inputPassword.text.length > 8){
                binding.btnRegister.setBackgroundResource(R.drawable.background_button_auth_active)
                binding.btnRegister.setTextColor(Color.parseColor("#FFFFFF"))
            } else if (binding.inputPassword.text.length <= 8 ){
                binding.btnRegister.setBackgroundResource(R.drawable.background_button_auth)
                binding.btnRegister.setTextColor(Color.parseColor("#9DA6B5"))
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textToLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.registerActionLogin)
        }

        binding.btnRegister.setOnClickListener{
            if (binding.inputEmail.text.isNullOrEmpty() ||
                binding.inputPassword.text.isNullOrEmpty() ||
                binding.inputUsername.text.isNullOrEmpty()
            ){
                Toast.makeText(activity, "username/email/password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val registerRequest = RegisterRequest(
                binding.inputUsername.text.toString(),
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()
            )

            NetworkConfig(context)
                .getService()
                .register(registerRequest)
                .enqueue(object : Callback<APIResponse<String>>{
                    override fun onResponse(
                        call: Call<APIResponse<String>>,
                        response: Response<APIResponse<String>>
                    ) {
                        if (response.body()?.status != HttpURLConnection.HTTP_OK){
                            Toast.makeText(
                                context,
                                "Register failed: Wrong usernam/email/password",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Navigation.findNavController(view).navigate(R.id.registerActionLogin)
                        }
                    }

                    override fun onFailure(call: Call<APIResponse<String>>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "Register failed: Check connection!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }


}