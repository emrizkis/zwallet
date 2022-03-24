package com.emrizkis.zwallet.ui.layout.transaction.transfer.confirmation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentTransferConfirmationBinding
import com.emrizkis.zwallet.ui.layout.transaction.transfer.TransferViewModel
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.*
import com.emrizkis.zwallet.utils.Helper.formatPrice
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TransferConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentTransferConfirmationBinding
    private val viewModel: TransferViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTransferConfirmationBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        agar scrollview jalan
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.btnContinue.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_transferConfirmationFragment_to_pinConfirmationTransactionFragment)
        }

        viewModel.apply {
            getSelectedContact().observe(viewLifecycleOwner){
                binding.apply {
                    nameContact.text = it?.name
                    phoneNumber.text = it?.phone
                    Glide.with(binding.imageReceiver).load(BASE_URL + it?.image)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(10))
                            .placeholder(R.drawable.ic_baseline_broken_image_24)).into(binding.imageReceiver)
                }
            }

            getTransferParam().observe(viewLifecycleOwner){ it ->
                binding.apply {
                    amount.formatPrice(it?.amount.toString())
                    balanceLeft.formatPrice((viewModel.balance.value!! - it?.amount!!).toString())

//                    binding.balanceLeft.text = viewModelProfile.getDataProfile().value?.toString()

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

}