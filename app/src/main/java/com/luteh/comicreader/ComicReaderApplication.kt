package com.luteh.comicreader

import android.app.Application
import com.luteh.comicreader.di.component.ApplicationComponent
import com.luteh.comicreader.di.component.DaggerApplicationComponent
import com.luteh.comicreader.di.module.ApplicationModule

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class ComicReaderApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        setup()
    }

    @Suppress("DEPRECATION")
    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: ComicReaderApplication private set
    }
}