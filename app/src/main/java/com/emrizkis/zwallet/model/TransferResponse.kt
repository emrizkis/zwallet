package com.emrizkis.zwallet.model


import com.google.gson.annotations.SerializedName

data class TransferResponse(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("receiver")
    val `receiver`: Int?,
    @SerializedName("sender")
    val sender: Int?,
    @SerializedName("type")
    val type: Int?
)