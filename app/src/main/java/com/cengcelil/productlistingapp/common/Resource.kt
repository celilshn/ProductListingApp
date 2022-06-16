package com.cengcelil.productlistingapp.common

data class Resource<out T>(
    val status: Status,
    val data: T?=null,
    val message:String?
){
    companion object{

        fun <T> success(data:T): Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error(msg:String, data:T?=null): Resource<T>{
            return Resource(Status.ERROR, data, msg)
        }
        fun <T> loading(msg:String,data:T?=null): Resource<T>{
            return Resource(Status.LOADING, data, msg)
        }
        fun <T> refresh(): Resource<T>{
            return Resource(Status.REFRESH, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    REFRESH
}