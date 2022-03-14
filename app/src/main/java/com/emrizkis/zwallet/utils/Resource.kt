package com.emrizkis.zwallet.utils


// ini untuk?
class Resource<out T> (val state: State, val data: T?, val message: String?) {
    companion object{
        fun <T> success(data: T): Resource<T> = Resource(State.SUCCESS, data, message = null)
        fun <T> error(data: T, message: String?): Resource<T> = Resource(State.ERROR, data, message)
        fun <T> loading(data: T): Resource<T> = Resource(State.LOADING, data, message = null)
    }
}