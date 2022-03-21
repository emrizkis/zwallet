package com.emrizkis.zwallet.ui.layout.main.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.FragmentHomeBinding
import com.emrizkis.zwallet.model.response.HomeUserResponse
import com.emrizkis.zwallet.ui.adapter.TransactionAdapter
import com.emrizkis.zwallet.ui.layout.main.HomeViewModel
import com.emrizkis.zwallet.ui.layout.main.profile.ProfileActivity
import com.emrizkis.zwallet.ui.layout.transaction.topup.TopupActivity
import com.emrizkis.zwallet.ui.layout.transaction.transfer.TransferActivity
import com.emrizkis.zwallet.utils.BASE_URL
import com.emrizkis.zwallet.utils.Helper.formatPrice
import com.emrizkis.zwallet.utils.PREFS_NAME
import com.emrizkis.zwallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection


@AndroidEntryPoint
class HomeFragment : Fragment() {
//    private var transactionData = mutableListOf<Transaction>()
//    lateinit var transactionAdapter: TransactionAdapter
//    private lateinit var binding: FragmentHomeBinding

//    binding to home xml
    private lateinit var binding: FragmentHomeBinding
    private var balanceLeft: Int = 0

    lateinit var transactionAdapter: TransactionAdapter
    private lateinit var prefs: SharedPreferences
    private val viewModel: HomeViewModel by activityViewModels()



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

//        to profile page
        binding.user.profileImage.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
        }

//        to transfer page
        binding.user.btnTransfer.setOnClickListener {
            val intent = Intent(activity, TransferActivity::class.java)
            startActivity(intent)
        }


//        to topup page
        binding.user.btnTopup.setOnClickListener {
            viewModel.setDataProfile(balanceLeft)
            val intent = Intent(activity,TopupActivity::class.java)
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

//                    var profile = HomeUserResponse(
//                        id = it.data?.data?.get(0)?.id.toString(),
//                        balance = it.data?.data?.get(0)?.balance.toString().toInt(),
//                        phone = it.data?.data?.get(0)?.phone.toString(),
//                        name = it.data?.data?.get(0)?.name.toString(),
//                        image = BASE_URL+(it.data.data?.get(0)?.image.toString())
//                    )
//                    viewModel.setDataProfile(profile)
                    balanceLeft = it.data.data?.get(0)?.balance.toString().toInt()
                    user.balance.formatPrice(it.data?.data?.get(0)?.balance.toString()) //it.data?.data?.get(0)?.balance.toString())
                    user.phoneNumber.text = it.data?.data?.get(0)?.phone
                    user.profileName.text = it.data?.data?.get(0)?.name

                    Glide.with(user.profileImage).load(BASE_URL+ (it.data.data?.get(0)?.image.toString()))
                        .apply(
                            RequestOptions.bitmapTransform(
                                RoundedCorners(10)
                            ).placeholder(R.drawable.ic_baseline_broken_image_24)
                        ).into(user.profileImage)
                }

            }
            else {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }

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
                        binding.apply {
                            loadingIndicator.visibility = View.GONE
                            recycleTransaction.visibility = View.VISIBLE
                        }

                        this.transactionAdapter.apply {
                            addData(it.data?.data!!)
                            notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(context, "${it.data?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recycleTransaction.visibility = View.VISIBLE
                    }
                    Toast.makeText(context,"${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}