package com.dev.softprodigyassignment.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.dev.softprodigyassignment.R
import com.dev.softprodigyassignment.databinding.ItemPhotoBinding
import com.dev.softprodigyassignment.ui.main.view.MainActivity
import kotlinx.android.synthetic.main.item_photo.view.*

private const val TAG = "mohit"
class PhotoAdapter(private val activity:MainActivity,private val photos: ArrayList<String>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var itemPhotoBinding=ItemPhotoBinding.inflate(LayoutInflater.from(activity),parent,false)
        return PhotoViewHolder(itemPhotoBinding)

    }


    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: photo ${photos[position]}")
        val circularProgressDrawable = CircularProgressDrawable(activity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(activity)
            .load(photos[position])
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(holder.itemPhotoBinding.ivPhoto)


    }

    fun addPhotos(photos: List<String>) {
        this.photos.apply {
            clear()
            addAll(photos)
        }

    }
    class PhotoViewHolder(var itemPhotoBinding: ItemPhotoBinding) : RecyclerView.ViewHolder(itemPhotoBinding.root)



}
