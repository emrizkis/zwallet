package com.emrizkis.zwallet.ui.layout.main.profile

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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentProfileBinding

import com.emrizkis.zwallet.ui.layout.auth.AuthActivity
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.*
import com.emrizkis.zwallet.utils.Helper.formatPrice
import dagger.hilt.android.AndroidEntryPoint
import java.net.HttpURLConnection


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var prefs: SharedPreferences
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        val getParams = (activity as ProfileActivity?)!!
//        binding.profileName.text = getParams.getName()
//        binding.phoneNumber.text = getParams.getPhone()


        val response = viewModel.getProfileInfo()

        response.observe(viewLifecycleOwner){
            when (it.state){
                State.LOADING->{
                    loadingDialog.start("Processing your request")
                }

                State.SUCCESS->{
                    if(it.data?.status == HttpURLConnection.HTTP_OK){


                        viewModel.name.value = "${it.data?.data?.firstname} ${it.data?.data?.lastname}"
                        viewModel.firstName.value = "${it.data?.data?.firstname}"
                        viewModel.lastName.value = "${it.data?.data?.lastname}"
                        viewModel.verifiedEmail.value = "${it.data?.data?.email}"
                        viewModel.phone.value = "${it.data?.data?.phone}"

                        binding.apply {
                            profileName.text =  viewModel.name.value.toString()
                            phoneNumber.text =  viewModel.phone.value.toString()
//                            balanceLeft = it.data.data?.get(0)?.balance.toString()
//                            user.balance.formatPrice(balanceLeft) //it.data?.data?.get(0)?.balance.toString())
//                            user.phoneNumber.text = it.data?.data?.get(0)?.phone
//                            user.profileName.text = it.data?.data?.get(0)?.name

                            Glide.with(profileImage).load(BASE_URL+ (it.data.data?.image.toString()))
                                .apply(
                                    RequestOptions.bitmapTransform(
                                        RoundedCorners(10)
                                    ).placeholder(R.drawable.ic_baseline_broken_image_24)
                                ).into(profileImage)
                        }

//                        loadingDialog.stop()
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

        return binding.root

    }
//
//    override fun onResume() {
//        super.onResume()
//
//        viewModel.name.value = viewModel.name.value.toString()
//
////        viewModel.firstName.value = "${it.data?.data?.firstname}"
////        viewModel.lastName.value = "${it.data?.data?.lastname}"
////        viewModel.verifiedEmail.value = "${it.data?.data?.email}"
//        viewModel.phone.value = viewModel.phone.value.toString()
//
//
//    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!

        binding.textEdit.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.backButton.setOnClickListener {
            activity?.finish()
        }

        binding.btnLogout.setOnClickListener{

            AlertDialog.Builder(context, R.style.dialogtheme)
                .setTitle("Logout Confirmation")
                .setMessage("Are you sure want to logout?")
                .setPositiveButton("Yes"){_,_ ->
                    with(prefs.edit()){
                        putBoolean(KEY_LOGGED_IN, false)
                        putString(KEY_USER_TOKEN, null)
                        putString(KEY_USER_REFRESH_TOKEN, null)
                        putString(KEY_USER_EMAIL, null)
                        apply()
                    }
                    val intent = Intent(activity, AuthActivity::class.java).apply {
//                       to clear all activity
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        putExtra("EXIT", true)
                    }
                    startActivity(intent)
//                    activity?.finish()
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

}