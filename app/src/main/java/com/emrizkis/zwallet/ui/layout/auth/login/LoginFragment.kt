package com.emrizkis.zwallet.ui.layout.auth.login

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
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentLoginBinding
import com.emrizkis.zwallet.ui.layout.auth.AuthViewModel
import com.emrizkis.zwallet.ui.layout.main.home.MainActivity
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.net.HttpURLConnection


@AndroidEntryPoint
class LoginFragment : Fragment() {

//    binding ke UI yang telah dibuat (untuk fragment)
    private lateinit var binding: FragmentLoginBinding
//    view model berguna jika halaman kita butuh data atau perlu push data ke server
    private val viewModel: AuthViewModel by activityViewModels()
//    ini tempat disimpannya pengaturan atau untuk persisten data
    private lateinit var preferences: SharedPreferences
//    untuk memunculkan login dialog
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding = FragmentLoginBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog(requireActivity())
        preferences = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        agar scrollview jalan
//        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)


//        saat password diketik, ini salah satu contoh untuk validasi form
        binding.inputPassword.addTextChangedListener {

            if(binding.inputPassword.text.length > 8){
                binding.btnLogin.setBackgroundResource(R.drawable.background_button_rounded_active)
                binding.btnLogin.setTextColor(Color.parseColor("#FFFFFF"))
            } else if (binding.inputPassword.text.length <= 8 ){
                binding.btnLogin.setBackgroundResource(R.drawable.background_button_rounded)
                binding.btnLogin.setTextColor(Color.parseColor("#9DA6B5"))
            }
        }


//        saat tombol login ditekan maka akan melakukan aksi ke API
        binding.btnLogin.setOnClickListener{
            if(binding.inputEmail.text.isNullOrEmpty() || binding.inputPassword.text.isNullOrEmpty()){

//                binding.inputEmail.text.toString().replace(" ", "")
//                muncul toast
                Toast.makeText(activity, "email/password is empty", Toast.LENGTH_SHORT).show()

//                tetap dihalaman ini
                return@setOnClickListener
            }

//            menjalankan fungsi pada model yang telah dibuat
            val response = viewModel.login(
                binding.inputEmail.text.toString().replace(" ", ""),
                binding.inputPassword.text.toString()
            )


//            observasi response dari server
            response.observe(viewLifecycleOwner){

                when (it.state){
                    State.LOADING->{
                        loadingDialog.start("Processing your request")
                    }

                    State.SUCCESS->{
                        if(it.data?.status == HttpURLConnection.HTTP_OK){
                            with(preferences.edit()){
                                putBoolean(KEY_LOGGED_IN, true)
                                putString(KEY_USER_EMAIL, it.data.data?.email)
                                putString(KEY_USER_TOKEN, it.data.data?.token)
                                putString(KEY_USER_REFRESH_TOKEN, it.data.data?.refreshToken)
                                apply()
                            }

                            if(it.data.data?.hasPin!!) {
                                Handler().postDelayed({
                                    val intent = Intent(activity, MainActivity::class.java)
                                    startActivity(intent)
                                    activity?.finish()
                                }, 1000)
                            }
                            else {
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_createPinFragment)
                            }

                        } else {
                            Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    State.ERROR->{
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT)
                            .show()

                    }

                }
            }
        }

        binding.textSignUp.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.loginActionRegister)
        }

        binding.textForgotPassword.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

    }


}


