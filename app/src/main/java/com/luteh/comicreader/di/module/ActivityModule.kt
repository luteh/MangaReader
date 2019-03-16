package com.luteh.comicreader.di.module

import android.app.Activity
import com.luteh.comicreader.ui.chapter.ChapterContract
import com.luteh.comicreader.ui.chapter.ChapterPresenter
import com.luteh.comicreader.ui.main.DiscoverContract
import com.luteh.comicreader.ui.main.DiscoverPresenter
import com.luteh.comicreader.ui.main.MainContract
import com.luteh.comicreader.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideMainPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Provides
    fun provideChapterPresenter(): ChapterContract.Presenter {
        return ChapterPresenter()
    }
}