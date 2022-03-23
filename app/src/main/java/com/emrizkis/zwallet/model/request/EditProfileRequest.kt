package com.emrizkis.zwallet.model.request

data class EditProfileRequest(
    val username : String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String
)
