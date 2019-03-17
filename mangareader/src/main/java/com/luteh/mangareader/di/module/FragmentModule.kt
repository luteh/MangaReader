package com.luteh.mangareader.di.module

import com.luteh.mangareader.ui.main.DiscoverContract
import com.luteh.mangareader.ui.main.DiscoverPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Luthfan Maftuh on 16/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Module
class FragmentModule {

    @Provides
    fun provideDiscoverPresenter(): DiscoverContract.Presenter{
        return DiscoverPresenter()
    }
}