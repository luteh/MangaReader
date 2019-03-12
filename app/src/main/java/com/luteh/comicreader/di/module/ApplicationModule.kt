package com.luteh.comicreader.di.module

import android.app.Application
import com.luteh.comicreader.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}