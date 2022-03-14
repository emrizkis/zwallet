package com.emrizkis.zwallet.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.emrizkis.zwallet.adapter.TransactionAdapter
import com.emrizkis.zwallet.databinding.FragmentHomeBinding
import com.emrizkis.zwallet.ui.profile.ProfileActivity
import com.emrizkis.zwallet.ui.viewModelsFactory
import com.emrizkis.zwallet.utils.PREFS_NAME
import com.emrizkis.zwallet.utils.State
import javax.net.ssl.HttpsURLConnection

class HomeFragment : Fragment() {
//    private var transactionData = mutableListOf<Transaction>()
//    lateinit var transactionAdapter: TransactionAdapter
//    private lateinit var binding: FragmentHomeBinding
    private lateinit var binding: FragmentHomeBinding
    lateinit var transactionAdapter: TransactionAdapter
    private lateinit var prefs: SharedPreferences
    private val viewModel: HomeViewModel by viewModelsFactory { HomeViewModel(requireActivity().application) }




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
        prefs = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        prepareData()


        binding.user.profileImage.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }

    }



    fun prepareData(){
        this.transactionAdapter = TransactionAdapter((listOf()))

        binding.recycleTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        viewModel.getBalance().observe(viewLifecycleOwner){
            if(it.data?.status == HttpsURLConnection.HTTP_OK){
                binding.apply {
                    user.balance.formatPrice(it.data?.data?.get(0)?.balance.toString())
                    user.phoneNumber.text = it.data?.data?.get(0)?.phone
                    user.profileName.text = it.data?.data?.get(0)?.name
                }

            }
            else {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }

            viewModel.getInvoice().observe(viewLifecycleOwner){
                when(it.state){
                    State.LOADING -> {
                        binding.apply {
                            loadingIndicator.visibility = View.VISIBLE
                            recycleTransaction.visibility = View.GONE
                        }

                    }

                    State.SUCCESS -> {
                        if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                            this.transactionAdapter.apply {
                                addData(it.data?.data!!)
                                notifyDataSetChanged()
                            }
                        } else {
                            Toast.makeText(context, it.data?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    else -> {
                        binding.apply {
                            loadingIndicator.visibility = View.GONE
                            recycleTransaction.visibility = View.VISIBLE
                        }
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }


    }


}