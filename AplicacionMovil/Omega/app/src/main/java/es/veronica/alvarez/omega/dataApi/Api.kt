package es.veronica.alvarez.omega.dataApi

object Api {

    private val retrofit = RetrofitBuilder.build()
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java)}
}