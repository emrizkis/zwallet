package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentSelectReceiverBinding
import com.emrizkis.zwallet.ui.adapter.ContactReceiverAdapter
import com.emrizkis.zwallet.ui.widget.LoadingDialog
import com.emrizkis.zwallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection
import androidx.fragment.app.activityViewModels


@AndroidEntryPoint

class SelectReceiverFragment : Fragment() {

    private lateinit var binding: FragmentSelectReceiverBinding
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var contactAdapter: ContactReceiverAdapter
    private val viewModel: TransferViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelectReceiverBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog(requireActivity())
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            activity?.finish()
        }

        this.contactAdapter = ContactReceiverAdapter(listOf()){
            contact,_ ->
            viewModel.setSelectedContact(contact)
            Navigation.findNavController(view).navigate(R.id.action_selectReceiverFragment_to_inputAmountFragment)
        }

        binding.recyclerContact.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contactAdapter
        }

        viewModel.getContact().observe(viewLifecycleOwner){
            when (it.state) {
                State.LOADING -> {
                    binding.apply {
                        loadingIndicator.visibility = View.VISIBLE
                        recyclerContact.visibility = View.GONE
                    }
                }
                State.SUCCESS -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recyclerContact.visibility = View.VISIBLE
                    }
                    if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                        this.contactAdapter.apply {
                            addData(it.data.data!!)
                            notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                State.ERROR -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recyclerContact.visibility = View.VISIBLE
                    }
                    Toast.makeText(context, "${ it.message }", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }


}