package com.example.youtube_api.main.network.inits

data class Resource <out T>(val networkStatus: NetworkStatus, val data:T?,val message:String?, val code:Int?){
companion object{
    fun <T> failed(message: String?, data:T?, code: Int?):Resource<T>{
        return Resource(NetworkStatus.FAILED,data,message,code)}
    fun <T>loading():Resource<T>{
        return Resource(NetworkStatus.LOADING, null,null,null)}
    fun <T> success(data:T?):Resource<T>{
        return Resource(NetworkStatus.SUCCESS,data,null,null)}
}
}
