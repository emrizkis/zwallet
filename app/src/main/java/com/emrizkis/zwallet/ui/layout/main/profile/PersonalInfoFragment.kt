package com.emrizkis.zwallet.ui.layout.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.databinding.FragmentPersonalInfoBinding
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import java.net.HttpURLConnection

@AndroidEntryPoint
class PersonalInfoFragment : Fragment() {
    private lateinit var binding: FragmentPersonalInfoBinding
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalInfoBinding.inflate(layoutInflater)

        val response = viewModel.getProfileInfo()

        response.observe(viewLifecycleOwner){
            when (it.state){
                State.LOADING->{
                    loadingDialog.start("Processing your request")
                }

                State.SUCCESS->{
                    if(it.data?.status == HttpURLConnection.HTTP_OK){
                        binding.firstName.text = it.data?.data?.firstname
                        binding.lastName.text = it.data?.data?.lastname
                        binding.verifiedEmail.text = it.data?.data?.email
                        binding.phoneNumber.text = it.data?.data?.phone

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
    }


}