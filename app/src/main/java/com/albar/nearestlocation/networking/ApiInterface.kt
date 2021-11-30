package com.albar.nearestlocation.networking

import com.albar.nearestlocation.data.response.ModelResultDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("place/nearbysearch/json")
    fun getDataResult(
        @Query("key") key: String,
        @Query("keyword") keyword: String,
        @Query("location") location: String,
        @Query("rankby") rankby: String
    ): Call<ModelResultDetail>

    @GET("place/details/json")
    fun getDetailResult(
        @Query("key") key: String,
        @Query("placeid") placeid: String
    ): Call<ModelResultDetail>
}