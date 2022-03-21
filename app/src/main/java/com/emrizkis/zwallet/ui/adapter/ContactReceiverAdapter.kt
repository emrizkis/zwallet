package com.emrizkis.zwallet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.emrizkis.zwallet.R
import com.emrizkis.zwallet.databinding.ExampleItemContactBinding
import com.emrizkis.zwallet.model.response.ReceiverResponse
import com.emrizkis.zwallet.utils.BASE_URL

class ContactReceiverAdapter(
    private var data: List<ReceiverResponse>,
    private val clickListener: (ReceiverResponse, View) -> Unit,
): RecyclerView.Adapter<ContactReceiverAdapter.ContactReceiverAdapterHolder>(){
    private lateinit var contextAdapter: Context

    class ContactReceiverAdapterHolder(private val binding: ExampleItemContactBinding): RecyclerView
        .ViewHolder(binding.root){

            fun bindData(data: ReceiverResponse, onClick: (ReceiverResponse, View)->Unit){
                binding.nameContact.text = data.name
                binding.phoneNumber.text = data.phone

                Glide.with(binding.imageProfile)
                    .load(BASE_URL+data.image)
                    .apply(RequestOptions.bitmapTransform(
                        RoundedCorners(10)).placeholder(R.drawable.ic_baseline_broken_image_24)
                    )
                    .into(binding.imageProfile)

                binding.root.setOnClickListener {
                    onClick(data, binding.root)
                }
            }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactReceiverAdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        this.contextAdapter = parent.context
        val binding = ExampleItemContactBinding.inflate(inflater, parent, false)
        return ContactReceiverAdapterHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ContactReceiverAdapterHolder,
        position: Int
    ) {
        holder.bindData(this.data[position], clickListener)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun addData(data: List<ReceiverResponse>){
        this.data = data
    }

}