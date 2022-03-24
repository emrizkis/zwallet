package com.emrizkis.zwallet.ui.layout.main.profile.personalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentPersonalInfoBinding
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileViewModel
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

        binding.firstName.text = viewModel.firstName.value
        binding.lastName.text = viewModel.lastName.value
        binding.verifiedEmail.text = viewModel.verifiedEmail.value
        binding.phoneNumber.text = viewModel.phone.value
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.phoneNumber.text = viewModel.phone.value

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.changePhone.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_personalInfoFragment_to_changePhoneNumberFragment)
        }
    }


}