package com.luteh.comicreader.common.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luteh.comicreader.di.component.DaggerActivityComponent
import com.luteh.comicreader.di.module.ActivityModule

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencyIfNeed()
    }

    protected abstract fun injectDependencyIfNeed()
}