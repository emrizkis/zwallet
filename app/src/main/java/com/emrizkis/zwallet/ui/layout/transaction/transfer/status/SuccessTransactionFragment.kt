package com.emrizkis.zwallet.ui.layout.transaction.transfer.status

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentSuccessTransactionBinding
import com.emrizkis.zwallet.ui.layout.transaction.transfer.TransferViewModel
import com.emrizkis.zwallet.utils.BASE_URL
import com.emrizkis.zwallet.utils.Helper.formatPrice
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SuccessTransactionFragment : Fragment() {

    private lateinit var binding: FragmentSuccessTransactionBinding
    private val viewModel: TransferViewModel by activityViewModels()
//    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSuccessTransactionBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        agar scrollview jalan
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        binding.btnToHome.setOnClickListener {
            activity?.overridePendingTransition(0,0)
            activity?.finish()
        }


        viewModel.apply {
            getSelectedContact().observe(viewLifecycleOwner){
                binding.apply {
                    nameContact.text = it?.name
                    phoneNumber.text = it?.phone
                    Glide.with(binding.imageReceiver).load(BASE_URL + it?.image)
                        .apply(
                            RequestOptions.bitmapTransform(RoundedCorners(10))
                            .placeholder(R.drawable.ic_baseline_broken_image_24)).into(binding.imageReceiver)
                }
            }


            getTransferParam().observe(viewLifecycleOwner){
                binding.apply {
                    amount.formatPrice(it?.amount.toString())
                    balanceLeft.formatPrice(it?.amount.toString())

                    if(it.notes.isNullOrEmpty()) {
                        binding.someNotes.text = "-"
                    } else {
                        binding.someNotes.text = it?.notes
                    }
                    //format Date
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val getTime = LocalDateTime.now()
                        val date = DateTimeFormatter.ofPattern("MMM dd, yyyy")
                        val time = DateTimeFormatter.ofPattern("hh:mma")

                        binding.date.text = getTime?.format(date)
                        binding.time.text = getTime?.format(time)
                    } else {
                        val getTime = Calendar.getInstance().time
                        val date = SimpleDateFormat("MMM dd, yyyy")
                        val time = SimpleDateFormat("hh:mma")
                        binding.date.text = date.format(getTime)
                        binding.time.text = time.format(getTime)
                    }
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()

//        var mainActivity = (activity as MainActivity?)!!
//        mainActivity.finish()
        activity?.overridePendingTransition(0,0)
        activity?.finish()
    }

}