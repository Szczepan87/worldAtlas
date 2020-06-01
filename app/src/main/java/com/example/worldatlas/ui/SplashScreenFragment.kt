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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.scope.lifecycleScope

class SplashScreenFragment : ScopedFragment() {

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

        launch {
            val allCountries = worldAtlasViewModel.countries.await()

            allCountries.observe(viewLifecycleOwner, Observer {
                if (!it.isNullOrEmpty()) {
                    val action =
                        SplashScreenFragmentDirections.actionSplashScreenFragmentToContinentFragment()
                    findNavController().navigate(action)
                    Log.d("MOVED TO CONTINENT", "FRAGMENT")
                    Log.d("FRAGMENT VM LIST FIRST ENTRY", "${allCountries.value?.first()}")
                } else if ( it.isNullOrEmpty()){
                    Log.d("SPLASH SCREEN", "LIST IS NULL OR EMPTY: ${it.isNullOrEmpty()}")
                    Snackbar.make(
                        this@SplashScreenFragment.binding.splashScreenLayout,
                        "Empty database",
                        Snackbar.LENGTH_LONG
                    )
                }
            })
        }
    }
}
