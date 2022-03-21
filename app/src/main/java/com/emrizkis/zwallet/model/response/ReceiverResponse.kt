package com.emrizkis.zwallet.model.response


import com.google.gson.annotations.SerializedName

data class ReceiverResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
)