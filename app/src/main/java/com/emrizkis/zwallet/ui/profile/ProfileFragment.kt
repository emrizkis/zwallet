package com.emrizkis.zwallet.ui.profile

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.window.SplashScreen
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentProfileBinding
import com.emrizkis.zwallet.ui.auth.AuthActivity
import com.emrizkis.zwallet.utils.KEY_LOGGED_IN
import com.emrizkis.zwallet.utils.PREFS_NAME

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var prefs: SharedPreferences
//    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!

        binding.btnLogout.setOnClickListener{

            AlertDialog.Builder(context)
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

    }

}