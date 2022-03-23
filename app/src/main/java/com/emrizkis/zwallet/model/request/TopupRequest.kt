package com.emrizkis.zwallet.model.request

data class TopupRequest(
    val phone: String,
    val amount: String
)
