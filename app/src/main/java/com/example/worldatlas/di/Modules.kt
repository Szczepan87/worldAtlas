package com.example.worldatlas.di

import com.example.worldatlas.ui.WorldAtlasViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WorldAtlasViewModel() }
}