package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentInputAmountBinding

class InputAmountFragment : Fragment() {

    private lateinit var binding: FragmentInputAmountBinding


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

        }
        binding.btnTransfer.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_inputAmountFragment_to_transferConfirmationFragment)
        }
    }

}