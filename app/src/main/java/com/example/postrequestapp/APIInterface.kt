package com.example.postrequestapp

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("test/")
    fun getAll(): Call<Users>

    @POST("test/")
    fun addUser(@Body userData: UsersItem): Call<UsersItem>

    @PUT("/test/{id}")
    fun updateUser(@Path("id") id: Int, @Body userData: UsersItem): Call<UsersItem>

    @DELETE("/test/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>

}