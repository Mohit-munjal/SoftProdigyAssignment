package com.dev.softprodigyassignment.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.dev.softprodigyassignment.R
import com.dev.softprodigyassignment.data.api.ApiHelper
import com.dev.softprodigyassignment.data.api.RetrofitBuilder
import com.dev.softprodigyassignment.databinding.FragmentHomeBinding
import com.dev.softprodigyassignment.ui.base.ViewModelFactory
import com.dev.softprodigyassignment.ui.main.adapter.PhotoAdapter
import com.dev.softprodigyassignment.ui.main.viewmodel.PhotoViewModel
import com.dev.softprodigyassignment.utils.Status

private const val TAG = "mohit"

class HomeFragment : Fragment() {

lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: PhotoViewModel
    lateinit var activity: MainActivity
    lateinit var photoAdapter:PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

            binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home, container, false)
            activity=context as MainActivity

            setupUI()
            setupViewModel()
            setupObservers()
            return binding.root

    }
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(PhotoViewModel::class.java)
    }
    private fun setupUI() {
        photoAdapter= PhotoAdapter(activity,arrayListOf())
        PagerSnapHelper().attachToRecyclerView(binding.rvImages)
        binding.rvImages.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=photoAdapter
        }
    }

    private fun setupObservers() {
        viewModel.getPhotos().observe(viewLifecycleOwner){ resource->
            when (resource.status) {
                Status.SUCCESS -> {
                    binding.rvImages.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Log.d(TAG, "setupObservers: data ${resource.data}")
                    resource.data?.let { photos -> retrieveList(photos) }
                }
                Status.ERROR -> {
                    binding.rvImages.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(activity, resource.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.rvImages.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }


        }

    }

    private fun retrieveList(photos: List<String>) {
        Log.d(TAG, "retrieveList: ")
        photoAdapter.apply {
            addPhotos(photos)
            notifyDataSetChanged()
        }
    }

}