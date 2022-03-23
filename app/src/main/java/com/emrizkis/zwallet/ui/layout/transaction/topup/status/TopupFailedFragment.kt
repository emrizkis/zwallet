package com.emrizkis.zwallet.ui.layout.transaction.topup.status

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentTopupFailedBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopupFailedFragment : Fragment() {
    private lateinit var binding: FragmentTopupFailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTopupFailedBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnToHome.setOnClickListener {
            activity?.finish()
        }

    }

    override fun onDetach() {
        super.onDetach()
        activity?.overridePendingTransition(0,0)
        activity?.finish()
    }

}