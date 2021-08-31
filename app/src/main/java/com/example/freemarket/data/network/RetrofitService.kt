package com.example.freemarket.data.network

import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface RetrofitService {

    class Creator {
        companion object {
            fun getRetrofitServiceAdapter(): RetrofitService {
                val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()
                val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BUILD_TYPE)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS).build())
                    .build()

                return retrofit.create(RetrofitService::class.java)
            }
        }
    }
}