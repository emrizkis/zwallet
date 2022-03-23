package com.emrizkis.zwallet.ui.layout.transaction.topup.setparams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentInputTopupAmountBinding
import com.emrizkis.zwallet.ui.layout.transaction.topup.TopupViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InputTopupAmountFragment : Fragment() {
    private lateinit var binding: FragmentInputTopupAmountBinding
    private val viewModel: TopupViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInputTopupAmountBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnTopup.setOnClickListener {
            viewModel.setAmount(binding.amount.text.toString())
            Navigation.findNavController(view).navigate(R.id.action_inputTopupAmountFragment_to_topupPinConfirmationFragment)
        }
    }

}