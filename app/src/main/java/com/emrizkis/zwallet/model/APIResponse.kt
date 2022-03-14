package com.emrizkis.zwallet.model

class APIResponse<T> (
    var status: Int,
    var message: String,
    var data: T?
    ){

}