package com.emrizkis.zwallet.model.response


import com.google.gson.annotations.SerializedName

data class HomeUserResponse(
    @SerializedName("balance")
    val balance: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
)