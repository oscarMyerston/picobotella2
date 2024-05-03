package com.example.picobotella.service

class ApiUtils {
    companion object {
        fun getApiService(): ApiService {
            return RetrofitCliente.getRetrofit().create(ApiService::class.java)
        }
    }
}