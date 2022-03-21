
package com.emrizkis.zwallet.model.response

import com.google.gson.annotations.SerializedName

data class BalanceRequest(
    @SerializedName("balance")
    val balance: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("lastname")
    val lastname: String?
)

