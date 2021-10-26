package com.example.webservicestwo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
//    @GET("Photos")
//    fun getPhotos(): Call<List<DataModel>>
        @GET("/posts/{id}")
    fun getData( @Path("id") id: Int): Call<List<DataModel>>
}