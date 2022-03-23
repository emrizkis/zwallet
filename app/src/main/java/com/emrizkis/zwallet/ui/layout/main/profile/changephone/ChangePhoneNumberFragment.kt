package com.emrizkis.zwallet.ui.layout.main.profile.changephone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentChangePhoneNumberBinding
import com.emrizkis.zwallet.model.request.EditProfileRequest
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileViewModel
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.State
import java.net.HttpURLConnection

class ChangePhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentChangePhoneNumberBinding

    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePhoneNumberBinding.inflate(layoutInflater)


        val response = viewModel.getProfileInfo()
        response.observe(viewLifecycleOwner){
            when (it.state){
                State.LOADING->{
                    loadingDialog.start("Processing your request")
                }

                State.SUCCESS->{
                    if(it.data?.status == HttpURLConnection.HTTP_OK){

                        firstName = it.data?.data?.firstname.toString()
                        lastName = it.data?.data?.lastname.toString()
                        email = it.data?.data?.email.toString()
                        binding.phoneNumber.setText(it.data?.data?.phone)

                        loadingDialog.stop()
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
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLogin.setOnClickListener {

                val response = viewModel.changeProfile(
                    EditProfileRequest(
                        username = "",
                        firstname = firstName,
                        lastname = lastName,
                        phone = binding.phoneNumber.text.toString(),
                        email = email
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
//                                activity?.finish()

                            }
//                        else {
//                            Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_SHORT)
//                                .show()
//                        }
                        }
                        State.ERROR->{
                            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
//                    activity?.finish()
//                    findNavController().popBackStack()
                }

            }

    }

}