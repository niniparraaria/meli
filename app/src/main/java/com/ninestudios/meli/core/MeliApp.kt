package com.ninestudios.meli.core

import android.app.Application
import com.ninestudios.meli.core.modules.networkModule
import com.ninestudios.meli.core.modules.remoteDataSourceModule
import com.ninestudios.meli.core.modules.repositoryModule
import com.ninestudios.meli.core.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MeliApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MeliApp)
            androidLogger()
            modules(networkModule, remoteDataSourceModule, repositoryModule, viewModelModule)
        }
    }
}