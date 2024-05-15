package es.veronica.alvarez.omega.DataApi

import android.content.Context
import retrofit2.Retrofit

object Api {

    private lateinit var retrofit: Retrofit
    lateinit var retrofitService: ApiService

    fun initialize(context: Context) {
        retrofit = RetrofitBuilder().build(context.applicationContext)
        retrofitService = retrofit.create(ApiService::class.java)
    }


}