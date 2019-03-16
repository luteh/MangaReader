package com.luteh.comicreader.di.component

import com.luteh.comicreader.di.module.FragmentModule
import com.luteh.comicreader.ui.discover.DiscoverFragment
import dagger.Component

/**
 * Created by Luthfan Maftuh on 16/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(discoverFragment: DiscoverFragment)
}