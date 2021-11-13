package com.dev.softprodigyassignment.data.repository

import com.dev.softprodigyassignment.data.api.ApiHelper

class PhotosRepository(private val apiHelper: ApiHelper) {

    suspend fun getPhotos() = apiHelper.getPhotos(10)
}
