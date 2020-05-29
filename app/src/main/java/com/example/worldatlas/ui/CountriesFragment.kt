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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.worldatlas.R
import com.example.worldatlas.databinding.FragmentCountriesBinding
import com.example.worldatlas.model.Country
import com.example.worldatlas.utils.CountryRecyclerAdapter
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
    private val recyclerAdapter = CountryRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val continentName = arguments.continent
        Log.d("COUNTRIES FRAGMENT", "THE CONTINENT NAME IS: $continentName")
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
                recyclerAdapter.updateList(it)
                Log.d("COUNTRIES FRAGMENT", "ADDING TO LOCAL LIST")
            })
        }

        binding.countriesRecycler.adapter = recyclerAdapter

        recyclerAdapter.onCountryClickListener = {
            Log.d("COUNTRIES FRAGMENT", "SENDING ${it.name} TO COUNTRIES DETAILS")
            val action = CountriesFragmentDirections.actionCountriesFragmentToCountryDetailsFragment(it)
            findNavController().navigate(action)
        }
    }
}
