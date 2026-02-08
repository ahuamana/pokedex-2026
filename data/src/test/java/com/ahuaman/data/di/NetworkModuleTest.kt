package com.ahuaman.data.di

import com.ahuaman.data.remote.PokeApiService
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import jakarta.inject.Inject
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit

@HiltAndroidTest
@UninstallModules(NetworkModule::class)
class NetworkModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var pokeApiService: PokeApiService

    @Inject
    lateinit var retrofit: Retrofit

    @Before
    fun init() {
        hiltRule.inject()
    }


}