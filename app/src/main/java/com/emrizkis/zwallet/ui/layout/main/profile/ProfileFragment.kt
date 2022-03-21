package com.emrizkis.zwallet.ui.layout.main.profile

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentProfileBinding

import com.emrizkis.zwallet.ui.layout.auth.AuthActivity
import com.emrizkis.zwallet.ui.layout.main.HomeViewModel
import com.emrizkis.zwallet.ui.layout.main.home.MainActivity
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.net.HttpURLConnection


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var prefs: SharedPreferences
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!

        binding.btnLogout.setOnClickListener{

            AlertDialog.Builder(context, R.style.dialogtheme)
                .setTitle("Logout Confirmation")
                .setMessage("Are you sure want to logout?")
                .setPositiveButton("Yes"){_,_ ->
                    with(prefs.edit()){
                        putBoolean(KEY_LOGGED_IN, false)
                        apply()
                    }
                    val intent = Intent(activity, AuthActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }.setNegativeButton("Cancel"){_,_ ->
                    return@setNegativeButton
                }.show()
        }

        binding.toPersonalInformation.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_personalInfoFragment)
        }

        binding.toChangePassword.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_changePasswordFragment)
        }

        binding.toChangePin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_changePinFragment)
        }
    }

    fun getDataProfil(){

    }

}