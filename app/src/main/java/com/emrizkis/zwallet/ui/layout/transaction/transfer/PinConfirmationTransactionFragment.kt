package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentPinConfirmationTransactionBinding

class PinConfirmationTransactionFragment : Fragment() {

    private lateinit var binding: FragmentPinConfirmationTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPinConfirmationTransactionBinding.inflate(layoutInflater)
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