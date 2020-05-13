package com.example.worldatlas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.worldatlas.R
import com.example.worldatlas.databinding.FragmentSplashScreenBinding
import org.koin.android.ext.android.get

class SplashScreenFragment : Fragment() {

    private val worldAtlasViewModel: WorldAtlasViewModel = get()
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
        with(binding) {
            lifecycleOwner = this@SplashScreenFragment
            viewModel = worldAtlasViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        worldAtlasViewModel.updateCountriesDatabase()
        worldAtlasViewModel.updateCountriesList()

        Log.d("FRAGMENT VM LIST FIRST ENTRY","${worldAtlasViewModel.countries.value?.first()}")
        Log.d("FRAGMENT DATABASE ERROR","${worldAtlasViewModel.exception.value?.message}")

        worldAtlasViewModel.exception.observe(viewLifecycleOwner, Observer {
            // TODO launch dialog with error info and dismiss app
        })

        worldAtlasViewModel.countries.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                val action =
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToContinentFragment()
                findNavController().navigate(action)
                Log.d("MOVED TO CONTINENT","FRAGMENT")
            }
        })
    }
}
