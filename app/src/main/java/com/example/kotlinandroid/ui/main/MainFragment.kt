package com.example.kotlinandroid.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kotlinandroid.data.MainViewModel
import com.example.kotlinandroid.databinding.MainFragmentBinding
import com.example.kotlinandroid.google.LiveDataActivity

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels{ MainViewModel.MainDataVMFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = MainFragmentBinding.inflate(inflater, container, false)

        viewModel.currentWeather.observe(viewLifecycleOwner, object :  Observer< String> {
            override fun onChanged(msg: String?) {
//                binding.message = t
                binding.message.text = msg
            }

        })

        binding.btnTest.setOnClickListener {
            val intent = Intent(context, LiveDataActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }



}