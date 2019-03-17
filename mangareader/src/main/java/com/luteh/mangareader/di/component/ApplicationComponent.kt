package com.luteh.mangareader.di.component

import com.luteh.mangareader.MangaReaderApplication
import com.luteh.mangareader.di.module.ApplicationModule
import dagger.Component

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: MangaReaderApplication)
}