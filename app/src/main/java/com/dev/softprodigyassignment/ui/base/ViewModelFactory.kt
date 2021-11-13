package com.dev.softprodigyassignment.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.softprodigyassignment.data.api.ApiHelper
import com.dev.softprodigyassignment.data.repository.PhotosRepository
import com.dev.softprodigyassignment.ui.main.viewmodel.PhotoViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            return PhotoViewModel(PhotosRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}