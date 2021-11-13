package com.dev.softprodigyassignment.data.api


class ApiHelper(private val apiService: ApiService) {

    suspend fun getPhotos(count:Int) = apiService.getPhotos(count, urls=true,httpsUrls=true)
}
