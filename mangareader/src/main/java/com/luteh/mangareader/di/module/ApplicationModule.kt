package com.luteh.mangareader.di.module

import android.app.Application
import com.luteh.mangareader.BuildConfig
import com.luteh.mangareader.MangaReaderApplication
import com.luteh.mangareader.di.scope.PerApplication
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Module
class ApplicationModule(private val mangaReaderApplication: MangaReaderApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return mangaReaderApplication
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG)
                    addInterceptor(ChuckInterceptor(MangaReaderApplication.context))
            }
            .build()
}