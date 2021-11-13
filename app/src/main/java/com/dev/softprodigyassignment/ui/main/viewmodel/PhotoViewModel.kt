package com.dev.softprodigyassignment.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dev.softprodigyassignment.data.repository.PhotosRepository
import com.dev.softprodigyassignment.utils.Resource
import kotlinx.coroutines.Dispatchers

class PhotoViewModel(private val photosRepository: PhotosRepository):ViewModel() {
    fun getPhotos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = photosRepository.getPhotos()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}