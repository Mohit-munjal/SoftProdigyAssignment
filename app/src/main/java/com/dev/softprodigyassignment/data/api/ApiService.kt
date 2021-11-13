package com.dev.softprodigyassignment.data.api

import retrofit2.http.*

interface ApiService {

    @GET("shibes?")
    suspend fun getPhotos(
        @Query("count") count:Int,
        @Query("urls") urls:Boolean,
        @Query("httpsUrls") httpsUrls:Boolean
    ): List<String>

}
