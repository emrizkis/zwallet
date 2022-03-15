package com.emrizkis.zwallet.ui.auth.pin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentCreatePinBinding

class CreatePinFragment : Fragment() {

    private lateinit var binding: FragmentCreatePinBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePinBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}