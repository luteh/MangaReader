package com.luteh.mangareader.di.component

import com.luteh.mangareader.di.module.FragmentModule
import com.luteh.mangareader.ui.fragment.discover.DiscoverFragment
import dagger.Component

/**
 * Created by Luthfan Maftuh on 16/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(discoverFragment: DiscoverFragment)
}