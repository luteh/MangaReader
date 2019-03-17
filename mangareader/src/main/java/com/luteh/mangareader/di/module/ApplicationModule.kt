package com.luteh.mangareader.di.module

import android.app.Application
import com.luteh.mangareader.MangaReaderApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Module
class ApplicationModule(private val mangaReaderApplication: MangaReaderApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mangaReaderApplication
    }
}