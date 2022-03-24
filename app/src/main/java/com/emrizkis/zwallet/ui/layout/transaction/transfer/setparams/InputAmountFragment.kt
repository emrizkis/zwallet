package com.emrizkis.zwallet.ui.layout.transaction.transfer.setparams

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentInputAmountBinding
import com.emrizkis.zwallet.model.request.TransferRequest
import com.emrizkis.zwallet.ui.layout.transaction.transfer.TransferViewModel
import com.emrizkis.zwallet.utils.BASE_URL
import com.emrizkis.zwallet.utils.Helper.formatNowBalance
import com.emrizkis.zwallet.utils.Helper.formatPrice
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String.format


@AndroidEntryPoint
class InputAmountFragment : Fragment() {

    private lateinit var binding: FragmentInputAmountBinding
    private val viewModel: TransferViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        binding = FragmentInputAmountBinding.inflate(layoutInflater)

        binding.balance.formatNowBalance(viewModel.balance.value.toString())

//        binding.balance.text = "Rp${viewModel.balance.value.toString()} Available"
//        binding.btnTransfer.text = getbalance.getDataProfile().value?.toString()
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        agar scrollview jalan
//        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.amount.addTextChangedListener {


            if (binding.amount.getNumericValue()!! > 10000 && binding.amount.getNumericValue()!! < viewModel.balance.value!!){

                binding.btnSubmit.setBackgroundResource(R.drawable.background_button_rounded_active)
                binding.btnSubmit.setTextColor(Color.parseColor("#FFFFFF"))
                binding.amount.setTextColor(Color.parseColor("#6379F4"))
            } else{
                binding.amount.setTextColor(Color.parseColor("#DFDCDC"))
                binding.btnSubmit.setBackgroundResource(R.drawable.background_button_rounded)
                binding.btnSubmit.setTextColor(Color.parseColor("#9DA6B5"))

            }

        }



            binding.btnSubmit.setOnClickListener {

                if (binding.amount.getNumericValue() > 10000 && binding.amount.getNumericValue()!! < viewModel.balance.value!!) {

                    viewModel.setTransferParam(
                        TransferRequest(
                            "",
                            binding.amount.getNumericValue().toInt(),
                            binding.someNotes.text.toString()
                        )
                    )
                    Navigation.findNavController(view)
                        .navigate(R.id.action_inputAmountFragment_to_transferConfirmationFragment)
                }
            }

            viewModel.getSelectedContact().observe(viewLifecycleOwner) {
                binding.nameContact.text = it?.name
                binding.phoneNumber.text = it?.phone
                Glide.with(binding.imageReceiver).load(BASE_URL + it?.image)
                    .apply(
                        RequestOptions.bitmapTransform(RoundedCorners(10))
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                    )
                    .into(binding.imageReceiver)
            }
        }

}