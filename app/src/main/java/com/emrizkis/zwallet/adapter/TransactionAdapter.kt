package com.emrizkis.zwallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.data.Transaction
import com.emrizkis.zwallet.model.Invoice
import com.emrizkis.zwallet.utils.BASE_URL
import com.google.android.material.imageview.ShapeableImageView

class TransactionAdapter(private var data: List<Transaction>): RecyclerView.Adapter<TransactionAdapter.TransactionAdapterHolder>() {
    lateinit var contextAdapter: Context
    class TransactionAdapterHolder(view: View): RecyclerView.ViewHolder(view){
        private val image: ShapeableImageView = view.findViewById(R.id.imageTransaction)
        private val name: TextView = view.findViewById(R.id.nameTransactionUser)
        private val type: TextView = view.findViewById(R.id.typeTransaction)
        private val amount: TextView = view.findViewById(R.id.amount)

        fun bindData(data: Transaction, context: Context, position: Int){
            name.text = data.transactionName
            type.text = data.transactionType
            amount.text = data.transactionNominal.toString()
//            image.setImageDrawable(data.transactionImage)
            Glide.with(image)
                .load(BASE_URL+data.image)
                .apply(RequestOptions.circleCropTransform()
                    .placeholder(R.drawable.img))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapterHolder {
//        biar bisa baca item transaction home
//        untuk membuat instance berupa ui reader
        val inflater = LayoutInflater.from(parent.context)
        this.contextAdapter = parent.context
        val inflatedView: View=inflater.inflate(R.layout.item_transaction_home, parent, false)
        return TransactionAdapterHolder(inflatedView)
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