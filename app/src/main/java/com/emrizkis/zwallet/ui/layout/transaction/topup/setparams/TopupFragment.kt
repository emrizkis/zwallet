package com.emrizkis.zwallet.ui.layout.transaction.topup.setparams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentTopupBinding
import com.emrizkis.zwallet.ui.layout.transaction.topup.TopupActivity
import com.emrizkis.zwallet.ui.layout.transaction.topup.TopupViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopupFragment : Fragment() {

    private lateinit var binding: FragmentTopupBinding
    private val viewModel: TopupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding = FragmentTopupBinding.inflate(layoutInflater)
        val getParams = (activity as TopupActivity?)!!
        viewModel.setPhone( getParams.getPhone())
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            activity?.finish()
        }
        binding.btnTopup.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_topupFragment_to_inputTopupAmountFragment)
        }
    }
}