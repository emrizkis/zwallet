package com.emrizkis.zwallet.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emrizkis.zwallet.adapter.TransactionAdapter
import com.emrizkis.zwallet.data.Transaction
import com.emrizkis.zwallet.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var transactionData = mutableListOf<Transaction>()
    lateinit var transactionAdapter: TransactionAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

//    fun prepareData(){
//
//        this.transactionData.add(
//            Transaction(getDrawable(R.drawable.sample01)!!,
//            transactionName = "Muhamad Rizki Septiawan",
//            transactionType = "Transfer",
//            transactionNominal = 125000.00
//        )
//        )
//
//        this.transactionAdapter.notifyDataSetChanged()
//
//    }


}