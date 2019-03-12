package com.luteh.comicreader.di.component

import com.luteh.comicreader.BaseApp
import com.luteh.comicreader.di.module.ApplicationModule
import dagger.Component

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: BaseApp)
}