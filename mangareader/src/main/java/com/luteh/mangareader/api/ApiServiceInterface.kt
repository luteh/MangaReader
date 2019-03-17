package com.luteh.mangareader.api

import com.luteh.mangareader.common.Constants
import com.luteh.mangareader.model.MangaList
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Luthfan Maftuh on 15/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
interface ApiServiceInterface {

    @GET("api/list/0/")
    fun getMangaList(
        @Query("p") index: Int,
        @Query("l") totalItems: Int = 25
    ): Observable<MangaList>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}