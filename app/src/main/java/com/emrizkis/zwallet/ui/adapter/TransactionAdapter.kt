package com.emrizkis.zwallet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.ItemTransactionHomeBinding
import com.emrizkis.zwallet.model.Invoice
import com.emrizkis.zwallet.utils.BASE_URL
import com.emrizkis.zwallet.utils.Helper.formatPrice

//get all transaction history

class TransactionAdapter(private var data: List<Invoice>): RecyclerView.Adapter<TransactionAdapter.TransactionAdapterHolder>() {
    lateinit var contextAdapter: Context

    class TransactionAdapterHolder(private val binding: ItemTransactionHomeBinding): RecyclerView
        .ViewHolder(binding.root){

//        private lateinit var binding:
//        private val image: ShapeableImageView = view.findViewById(R.id.imageTransaction)
//        private val name: TextView = view.findViewById(R.id.nameTransactionUser)
//        private val type: TextView = view.findViewById(R.id.typeTransaction)
//        private val amount: TextView = view.findViewById(R.id.amount)

//        @SuppressLint("CheckResult")
        fun bindData(data: Invoice, context: Context, position: Int){
            binding.nameTransactionUser.text = data.name
            binding.typeTransaction.text = data.type?.uppercase()
            binding.amount.formatPrice(data.amount.toString())
//            image.setImageDrawable(data.transactionImage)
            Glide.with(binding.imageTransaction).load(BASE_URL + data.image)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10))
                    .placeholder(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imageTransaction)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapterHolder {
//        biar bisa baca item transaction home
//        untuk membuat instance berupa ui reader
        val inflater = LayoutInflater.from(parent.context)
        this.contextAdapter = parent.context
        val binding = ItemTransactionHomeBinding.inflate(inflater, parent, false)
        return TransactionAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionAdapterHolder, position: Int) {
//        binding data
        holder.bindData(this.data[position], contextAdapter, position)

    }

    override fun getItemCount(): Int {
//        melihat banyak data
        return this.data.size
    }

    fun addData(data: List<Invoice>){
        this.data = data
    }
}