package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentTransferConfirmationBinding
import com.emrizkis.zwallet.ui.layout.main.home.MainActivity
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.*
import com.emrizkis.zwallet.utils.Helper.formatPrice
import java.net.HttpURLConnection
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
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.btnContinue.setOnClickListener {
            val response = viewModel.transferAmount( viewModel.getTransferParam())

            response.observe(viewLifecycleOwner){

                when (it.state){
                    State.LOADING->{
                        loadingDialog.start("Processing your request")
                    }

                    State.SUCCESS->{
                        if(it.data?.status == HttpURLConnection.HTTP_OK){
                            Navigation.findNavController(view).navigate(R.id.action_transferConfirmationFragment_to_successTransactionFragment)
                        } else {
                            Navigation.findNavController(view).navigate(R.id.action_transferConfirmationFragment_to_failedTransactionFragment)
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

}