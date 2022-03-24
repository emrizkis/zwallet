package com.emrizkis.zwallet.ui.layout.transaction.topup.setparams

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
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
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding = FragmentInputTopupAmountBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.amount.addTextChangedListener {


            if (binding.amount.getNumericValue()!! > 10000){

                binding.btnSubmit.setBackgroundResource(R.drawable.background_button_rounded_active)
                binding.btnSubmit.setTextColor(Color.parseColor("#FFFFFF"))
                binding.amount.setTextColor(Color.parseColor("#6379F4"))
            } else{
                binding.amount.setTextColor(Color.parseColor("#DFDCDC"))
                binding.btnSubmit.setBackgroundResource(R.drawable.background_button_rounded)
                binding.btnSubmit.setTextColor(Color.parseColor("#9DA6B5"))

            }

        }



        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.setAmount(binding.amount.text.toString())
            Navigation.findNavController(view).navigate(R.id.action_inputTopupAmountFragment_to_topupPinConfirmationFragment)
        }
    }

}