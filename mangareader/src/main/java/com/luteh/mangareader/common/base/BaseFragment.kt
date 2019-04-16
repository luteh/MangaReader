package com.luteh.mangareader.common.base


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.luteh.mangareader.di.component.DaggerFragmentComponent
import com.luteh.mangareader.di.component.FragmentComponent
import com.luteh.mangareader.di.module.FragmentModule


/**
 * Created by Luthfan Maftuh on 16/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
abstract class BaseFragment : Fragment() {

    lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        if (!::fragmentComponent.isInitialized) {
            fragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
    }

    abstract fun onInit()
}