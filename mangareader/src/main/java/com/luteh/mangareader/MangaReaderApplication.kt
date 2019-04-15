package com.luteh.mangareader

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.luteh.mangareader.di.component.ApplicationComponent
import com.luteh.mangareader.di.component.DaggerApplicationComponent
import com.luteh.mangareader.di.module.ApplicationModule
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class MangaReaderApplication : Application() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        context = this
        instance = this

        injectApplicationComponent()

        client = okHttpClient
    }

    @Suppress("DEPRECATION")
    fun injectApplicationComponent() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: MangaReaderApplication private set

        @SuppressLint("StaticFieldLeak")
                /**
                 * The application [Context] made static.
                 * Do **NOT** use this as the context for a view,
                 * this is mostly used to simplify calling of resources
                 * (esp. String resources) outside activities.
                 */
        var context: Context? = null
            private set

        var client: OkHttpClient? = null
            private set
    }
}