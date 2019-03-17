package com.luteh.mangareader.di.component

import com.luteh.mangareader.di.module.ActivityModule
import com.luteh.mangareader.ui.chapter.ChapterActivity
import dagger.Component

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(chapterActivity: ChapterActivity)
}