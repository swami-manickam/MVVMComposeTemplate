package com.compose.mvvm.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.compose.mvvm.common.AppConstants
import com.compose.mvvm.data.local.AppDatabaseService
import com.compose.mvvm.data.local.DatabaseService
import com.compose.mvvm.data.local.MvvmAppDatabase
import com.compose.mvvm.data.network.ApiKeyInterceptor
import com.compose.mvvm.data.network.ApiService
import com.compose.mvvm.utils.logger.AppLogger
import com.compose.mvvm.utils.logger.Logger
import com.compose.mvvm.utils.network.DefaultDispatcherProvider
import com.compose.mvvm.utils.network.DispatcherProvider
import com.compose.mvvm.utils.network.NetworkHelper
import com.compose.mvvm.utils.network.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor):
            OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkAPIKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)


    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstants.BASE_URL


    @NetworkAPIKey
    @Provides
    fun provideApiKey(): String = AppConstants.API_KEY

    @Provides
    @Singleton
    fun provideDatabaseService(appDatabase: MvvmAppDatabase): DatabaseService {
        return AppDatabaseService(appDatabase)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = AppConstants.DATABASE_NAME

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): MvvmAppDatabase {
        return Room.databaseBuilder(
            context,
            MvvmAppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager {
        return WorkManager.getInstance(context)
    }
}