package com.luteh.mangareader.common.base

import androidx.lifecycle.ViewModel
import com.luteh.mangareader.di.component.DaggerViewModelComponent
import com.luteh.mangareader.di.component.ViewModelComponent
import com.luteh.mangareader.di.module.NetworkModule
import com.luteh.mangareader.ui.main.DiscoverPresenter

/**
 * Created by Luthfan Maftuh on 15/04/2019.
 * Email luthfanmaftuh@gmail.com
 */
abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is DiscoverPresenter -> injector.inject(this)
        }
    }
}