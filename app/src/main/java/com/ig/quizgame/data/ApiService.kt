package com.ig.quizgame.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("be3ad627-5a7e-4437-a472-724078da858e")

    fun getCourse(): Call<Question?>
}