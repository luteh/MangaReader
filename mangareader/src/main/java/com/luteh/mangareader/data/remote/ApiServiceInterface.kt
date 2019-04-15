package com.luteh.mangareader.data.remote

import com.luteh.mangareader.MangaReaderApplication
import com.luteh.mangareader.common.Constants
import com.luteh.mangareader.data.remote.ApiEndPoint.ENDPOINT_MANGA_LIST
import com.luteh.mangareader.data.model.MangaListResponse
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Luthfan Maftuh on 15/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
interface ApiServiceInterface {

    @GET(ENDPOINT_MANGA_LIST)
    fun getMangaList(
//        @Query("p") index: Int
//        @Query("l") totalItems: Int = 25
    ): Observable<MangaListResponse>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(MangaReaderApplication.client!!)
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}