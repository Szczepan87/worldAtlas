package com.example.worldatlas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

import com.example.worldatlas.R
import com.example.worldatlas.databinding.FragmentCountriesBinding
import com.example.worldatlas.model.Country
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

/**
 * A simple [Fragment] subclass.
 */
class CountriesFragment : ScopedFragment() {

    private val worldAtlasViewModel: WorldAtlasViewModel = get()
    private lateinit var binding: FragmentCountriesBinding
    private val arguments: CountriesFragmentArgs by navArgs()
    private var listOfContinentCountries = mutableListOf<Country>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val continentName = arguments.continent
        worldAtlasViewModel.getCountriesByContinent(continentName)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)
        with(binding) {
            lifecycleOwner = this@CountriesFragment
            viewModel = worldAtlasViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launch {
            val countriesByContinent: LiveData<List<Country>> =
                worldAtlasViewModel.countriesByContinent.await()

            countriesByContinent.observe(viewLifecycleOwner, Observer {
                listOfContinentCountries.addAll(it)
                Log.d("COUNTRIES FRAGMENT", "ADDING TO LOCAL LIST")
            })
        }

        binding.countriesLayout.setOnClickListener {
            Toast.makeText(
                this@CountriesFragment.context,
                listOfContinentCountries.first().name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
