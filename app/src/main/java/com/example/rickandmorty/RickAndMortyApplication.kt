package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.databaseModule
import com.example.rickandmorty.di.networkingModule
import com.example.rickandmorty.di.repositoryModule
import com.example.rickandmorty.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(networkingModule, databaseModule, repositoryModule, viewModelModule)

        startKoin {
            androidLogger()
            androidContext(this@RickAndMortyApplication)
            modules(modules)
        }
    }
}
