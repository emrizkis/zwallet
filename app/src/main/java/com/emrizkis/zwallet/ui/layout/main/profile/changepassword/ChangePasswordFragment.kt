package com.emrizkis.zwallet.ui.layout.main.profile.changepassword

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.databinding.FragmentChangePasswordBinding
import com.emrizkis.zwallet.model.request.ChangePasswordRequest
import com.emrizkis.zwallet.ui.layout.auth.AuthViewModel
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileViewModel
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.State
import java.net.HttpURLConnection

class ChangePasswordFragment : Fragment() {

    lateinit var et_code: EditText
    lateinit var etPassword:EditText
    lateinit var etRepeatPassword: EditText
    val MIN_PASSWORD_LENGTH = 6;

    private lateinit var binding: FragmentChangePasswordBinding
    private val viewModel: ProfileViewModel by activityViewModels()
    //    ini tempat disimpannya pengaturan atau untuk persisten data
    private lateinit var preferences: SharedPreferences
    //    untuk memunculkan login dialog
    private lateinit var loadingDialog: LoadingDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding = FragmentChangePasswordBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInitialization()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLogin.setOnClickListener {

            if(binding.inputCurrentPassword.text.isNullOrEmpty()
                || binding.inputNewPassword1.text.isNullOrEmpty()
                || binding.inputNewPassword2.text.isNullOrEmpty() ){

                performResetPassword(view)
//                muncul toast
                Toast.makeText(activity, "password is empty", Toast.LENGTH_SHORT).show()

//                tetap dihalaman ini
                return@setOnClickListener
            }

            val response = viewModel.changePassword(
                ChangePasswordRequest(
                    old_password =  binding.inputCurrentPassword.text.toString(),
                    new_password = binding.inputNewPassword2.text.toString()
                )
            )

            response.observe(viewLifecycleOwner){
                when(it.state){
                    State.LOADING -> {
                        loadingDialog.start("Processing your request")
                    }
                    State.SUCCESS -> {
                        if(it.data?.status == HttpURLConnection.HTTP_OK){
                            findNavController().popBackStack()
                        } else{
                            et_code.setError("Please Enter Valid Code")
                            validateInput()
                            Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
                    State.ERROR -> {
                        et_code.setError("Please Enter Valid Code")
                        validateInput()
                        Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

//    validation function
    fun viewInitialization(){

        et_code = binding.inputCurrentPassword
        etPassword = binding.inputNewPassword1
        etRepeatPassword = binding.inputNewPassword2
    }

    // Checking if the input in form is valid
    fun validateInput() {
        if (et_code.text.toString().equals("")) {
            et_code.setError("Please Enter Valid Code")
        }
        if (etPassword.text.toString().equals("")) {
            etPassword.setError("Please Enter Password")
        }
        if (etRepeatPassword.text.toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password")
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters")
        }

        // Checking if repeat password is same
        if (!etPassword.text.toString().equals(etRepeatPassword.text.toString())) {
            etRepeatPassword.setError("Password does not match")
        }
    }

//    Hook click Event
    fun performResetPassword(view: View) {
        validateInput()
//        if (validateInput()) {
//
//            // Input is valid, here send data to your server
//            val et_code = et_code.text.toString()
//            val password = etPassword.text.toString()
//            val repeatPassword = etRepeatPassword.text.toString()
//
//            Toast.makeText(context,"Password Reset Successfully",Toast.LENGTH_SHORT).show()
//            // Here you can call you API
//
//        }
    }

}