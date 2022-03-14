package com.emrizkis.zwallet.ui.home

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.adapter.TransactionAdapter
import com.emrizkis.zwallet.data.Transaction
import com.emrizkis.zwallet.databinding.ActivityMainBinding
import com.emrizkis.zwallet.model.APIResponse
import com.emrizkis.zwallet.model.UserDetail
import com.emrizkis.zwallet.network.NetworkConfig
import com.emrizkis.zwallet.ui.auth.AuthActivity
import com.emrizkis.zwallet.ui.profile.ProfileActivity
import com.emrizkis.zwallet.utils.KEY_LOGGED_IN
import com.emrizkis.zwallet.utils.PREFS_NAME
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MainActivity() : AppCompatActivity() {
//let init itu harus diisi di function, atau dimanapun
    private var transactionData = mutableListOf<Transaction>()
    lateinit var transactionAdapter: TransactionAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        
        setContentView(binding.root)

        this.transactionAdapter = TransactionAdapter(transactionData)
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.recycleTransaction.layoutManager = layoutManager
        binding.recycleTransaction.adapter = transactionAdapter

        prepareData()

        binding.user.profileImage.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

//            AlertDialog.Builder(this)
//                .setTitle("Logout Confirmation")
//                .setMessage("Are you sure want to logout?")
//                .setPositiveButton("Yes"){_,_ ->
//                    with(prefs.edit()){
//                        putBoolean(KEY_LOGGED_IN, false)
//                        apply()
//                    }
//                    val intent = Intent(this, AuthActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }.setNegativeButton("Cancel"){_,_ ->
//                    return@setNegativeButton
//                }.show()
        }
    }

    fun prepareData(){
        NetworkConfig(this)
            .getService()
            .getBalance()
            .enqueue(object : Callback<APIResponse<List<UserDetail>>>{
                override fun onResponse(
                    call: Call<APIResponse<List<UserDetail>>>,
                    response: Response<APIResponse<List<UserDetail>>>
                ) {
                    if (response.body()?.status != HttpURLConnection.HTTP_OK){
                        Toast.makeText(
                            applicationContext,
                            "fetch data failed: Check connection!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else{
                        val res = response.body()!!.data?.get(0)
                        binding.user.profileName.text = res?.name
                        binding.user.balance.text = res?.balance.toString()
                        if (res?.phone == null){
                            binding.user.phoneNumber.text = "phone number not set"
                         }else{
                            binding.user.phoneNumber.text = res?.phone

                        }


                    }
                }

                override fun onFailure(call: Call<APIResponse<List<UserDetail>>>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })

        this.transactionData.add(Transaction(getDrawable(R.drawable.sample01)!!,
            transactionName = "Muhamad Rizki Septiawan",
            transactionType = "Transfer",
            transactionNominal = 125000.00
        ))

        this.transactionAdapter.notifyDataSetChanged()

    }
}