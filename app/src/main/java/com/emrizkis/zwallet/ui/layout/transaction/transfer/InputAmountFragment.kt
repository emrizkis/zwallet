package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentInputAmountBinding
import com.emrizkis.zwallet.model.request.TransferRequest
import com.emrizkis.zwallet.utils.BASE_URL
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InputAmountFragment : Fragment() {

    private lateinit var binding: FragmentInputAmountBinding
    private val viewModel: TransferViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInputAmountBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }




        binding.btnTransfer.setOnClickListener {
            viewModel.setTransferParam(TransferRequest("",
            binding.amount.text.toString().toInt(),
            binding.someNotes.text.toString()))

            Navigation.findNavController(view).navigate(R.id.action_inputAmountFragment_to_transferConfirmationFragment)
        }

        viewModel.getSelectedContact().observe(viewLifecycleOwner){
            binding.nameContact.text = it?.name
            binding.phoneNumber.text = it?.phone
            Glide.with(binding.imageReceiver).load(BASE_URL+it?.image)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10))
                    .placeholder(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imageReceiver)
        }
    }

}