package com.emrizkis.zwallet.ui.layout.main.profile.editprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentEditProfileBinding
import com.emrizkis.zwallet.model.request.ChangePasswordRequest
import com.emrizkis.zwallet.model.request.EditProfileRequest
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileViewModel
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.State
import java.net.HttpURLConnection

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater)


        binding.firstName.setText(viewModel.firstName.value)
        binding.lastName.setText(viewModel.lastName.value)
        binding.verifiedEmail.setText(viewModel.verifiedEmail.value)
        binding.phoneNumber.setText(viewModel.phone.value)

        binding.firstName.requestFocus()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSave.setOnClickListener {

            val response = viewModel.changeProfile(
                EditProfileRequest(
                    username = "",
                    firstname = binding.firstName.text.toString(),
                    lastname = binding.lastName.text.toString(),
                    phone = binding.phoneNumber.text.toString(),
                    email = binding.verifiedEmail.text.toString()
                )
            )

            response.observe(viewLifecycleOwner){
                when (it.state){
                    State.LOADING->{
                        loadingDialog.start("Processing your request")
                    }

                    State.SUCCESS->{
                        if(it.data?.status == HttpURLConnection.HTTP_OK){
                            findNavController().popBackStack()
                        }
//
                    }
                    State.ERROR->{
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }
    }

}