package com.luteh.mangareader.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.luteh.mangareader.R


/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {

    private val TAG = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencyIfNeed()
    }

    protected abstract fun injectDependencyIfNeed()

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        onInit()
    }

    abstract fun onInit()


    // Replace current Fragment with the destination Fragment.
    fun replaceFragment(destFragment: Fragment, titleResId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_home_container, destFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        setTitle(titleResId)
    }
}