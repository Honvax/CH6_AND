package com.alfrsms.and_ch6.service

import com.alfrsms.and_ch6.model.Data
import com.alfrsms.and_ch6.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApiInterface {
    @GET("3/movie/popular?api_key=b0c20427847dad9a3c9548e1a59d1a66")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("3/movie/{movie_id}?api_key=b0c20427847dad9a3c9548e1a59d1a66")
    fun getDetailsMovies(@Path("movie_id") id: Int?): Call<Data>
}