package ru.raullek.weatherapp.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.raullek.weatherapp.BuildConfig
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://api.openweathermap.org/").build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().setPrettyPrinting().create()
    }

    @Provides
    @Singleton
    fun getTimeOut(): Int {
        return 30
    }

    @Provides
    @Singleton
    fun provideOkHttpClientDefault(
        interceptor: HttpLoggingInterceptor,
        timeout: Int
    ): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(interceptor)
        okBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
        return okBuilder.build()
    }

}