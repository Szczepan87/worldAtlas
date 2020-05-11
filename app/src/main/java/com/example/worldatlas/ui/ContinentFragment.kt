package com.example.worldatlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.worldatlas.R
import com.example.worldatlas.databinding.FragmentContinentBinding
import org.koin.android.ext.android.get

/**
 * A simple [Fragment] subclass.
 */
class ContinentFragment : Fragment() {

    private val worldAtlasViewModel: WorldAtlasViewModel = get()
    private lateinit var binding: FragmentContinentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_continent, container, false)
        with(binding) {
            lifecycleOwner = this@ContinentFragment
            viewModel = worldAtlasViewModel
        }
        return binding.root
    }
}
