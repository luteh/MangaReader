package com.luteh.mangareader.di.component

import com.luteh.mangareader.di.module.NetworkModule
import com.luteh.mangareader.ui.main.DiscoverPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 15/04/2019.
 * Email luthfanmaftuh@gmail.com
 */

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelComponent {

    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
//    fun inject(postListViewModel: PostListViewModel)
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param postViewModel PostViewModel in which to inject the dependencies
     */
//    fun inject(postViewModel: PostViewModel)

    fun inject(discoverPresenter: DiscoverPresenter)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }
}