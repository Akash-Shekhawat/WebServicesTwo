package com.example.webservicestwo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getData(): Call<List<DataModel>>

}