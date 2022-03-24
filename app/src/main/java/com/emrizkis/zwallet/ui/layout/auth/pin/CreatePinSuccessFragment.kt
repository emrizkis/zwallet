package com.emrizkis.zwallet.ui.layout.auth.pin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentCreatePinSuccessBinding
import com.emrizkis.zwallet.ui.layout.main.home.MainActivity

class CreatePinSuccessFragment : Fragment() {

    private lateinit var binding: FragmentCreatePinSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreatePinSuccessBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onDetach() {
        super.onDetach()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}