package com.luteh.comicreader.di.component

import android.app.Activity
import com.luteh.comicreader.di.module.ActivityModule
import com.luteh.comicreader.ui.chapter.ChapterActivity
import com.luteh.comicreader.ui.main.MainActivity
import dagger.Component

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(chapterActivity: ChapterActivity)
}