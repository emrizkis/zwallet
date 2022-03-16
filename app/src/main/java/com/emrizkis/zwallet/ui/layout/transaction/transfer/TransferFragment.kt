package com.emrizkis.zwallet.ui.layout.transaction.transfer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emrizkis.zwallet.databinding.FragmentTransferBinding
import com.emrizkis.zwallet.ui.layout.main.home.MainActivity

class TransferFragment : Fragment(){

    private lateinit var binding: FragmentTransferBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTransferBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {

            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)

        }
    }

}