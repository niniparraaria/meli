package com.ninestudios.meli.core.modules

import com.ninestudios.meli.cocktail_detail.CocktailDetailRepository
import com.ninestudios.meli.cocktail_detail.CocktailDetailViewModel
import com.ninestudios.meli.cocktails.CocktailsRepository
import com.ninestudios.meli.cocktails.CocktailsViewModel
import com.ninestudios.meli.core.network.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel{ CocktailsViewModel(get()) }
    viewModel{ CocktailDetailViewModel(get()) }
}

val repositoryModule = module {
    factory {  CocktailsRepository(get(), androidContext()) }
    factory {  CocktailDetailRepository(get(), androidContext()) }
}

val remoteDataSourceModule= module {
    factory {  RemoteDataSource(get()) }
}