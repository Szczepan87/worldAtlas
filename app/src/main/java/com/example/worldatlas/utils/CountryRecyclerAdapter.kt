package com.example.worldatlas.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldatlas.databinding.CountryCardBinding
import com.example.worldatlas.model.Country

class CountryRecyclerAdapter : RecyclerView.Adapter<CountryRecyclerAdapter.CountryViewHolder>() {
    private val countriesList = mutableListOf<Country>()

    fun updateList(listOfCountries: List<Country>) {
        countriesList.addAll(listOfCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CountryCardBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount() = countriesList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    inner class CountryViewHolder(private val binding: CountryCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(countryItem: Country) {
            binding.country = countryItem
        }
    }
}