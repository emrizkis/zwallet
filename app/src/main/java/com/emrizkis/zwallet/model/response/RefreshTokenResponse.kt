package com.emrizkis.zwallet.model.response


import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    @SerializedName("email")
    val email: String?,
    @SerializedName("expired_at")
    val expiredAt: Long?,
    @SerializedName("expired_in")
    val expiredIn: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("token")
    val token: String?
)