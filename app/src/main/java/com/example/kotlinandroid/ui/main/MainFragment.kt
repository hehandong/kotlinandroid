package com.example.kotlinandroid.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kotlinandroid.R
import com.example.kotlinandroid.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels{ MainViewModel.LiveDataVMFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        FragmentDeviceBinding.inflate(inflater, container, false)

        val binding = MainFragmentBinding.inflate(inflater, container, false)

        viewModel.currentWeather.observe(viewLifecycleOwner, object :  Observer< String> {
            override fun onChanged(msg: String?) {
//                binding.message = t
                binding.message.text = msg
            }

        })

        return binding.root
    }



}