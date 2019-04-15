package com.luteh.mangareader.di.module

import android.app.Activity
import com.luteh.mangareader.ui.activity.chapter.ChapterContract
import com.luteh.mangareader.ui.activity.chapter.ChapterPresenter
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
    fun provideChapterPresenter(): ChapterContract.Presenter {
        return ChapterPresenter()
    }
}