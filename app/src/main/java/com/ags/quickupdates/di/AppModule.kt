package com.ags.quickupdates.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.ags.quickupdates.util.Constant
import com.google.firebase.auth.FirebaseAuth
import com.kwabenaberko.newsapilib.NewsApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager =
        CredentialManager.create(context)

    @Provides
    fun provideNewsApiClient(): NewsApiClient =
        NewsApiClient(Constant.API_KEY)
}