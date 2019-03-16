package com.luteh.comicreader.common.base


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


/**
 * Created by Luthfan Maftuh on 16/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    protected abstract fun injectDependency()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
    }

    abstract fun onInit()
}