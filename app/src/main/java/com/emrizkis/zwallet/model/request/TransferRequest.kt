package com.emrizkis.zwallet.model.request

data class TransferRequest(
    var receiver: String,
    var amount: String,
    var notes: String?
)
