package com.luteh.comicreader.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luteh.comicreader.R
import com.luteh.comicreader.common.base.BaseActivity
import com.luteh.comicreader.ui.discover.DiscoverFragment
import kotlinx.android.synthetic.main.activity_home.*
import com.luteh.comicreader.common.utils.BottomNavigationBehavior
import androidx.coordinatorlayout.widget.CoordinatorLayout


class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.luteh.comicreader.R.layout.activity_home)
    }

    override fun injectDependencyIfNeed() {

    }

    override fun onInit() {
        replaceFragment(DiscoverFragment())

        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        bnv_home.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val layoutParams = bnv_home.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            com.luteh.comicreader.R.id.nav_discover -> {
//                Toast.makeText(, "Clicked", Toast.LENGTH_SHORT).show();
                replaceFragment(DiscoverFragment())
                return@OnNavigationItemSelectedListener true
            }
            com.luteh.comicreader.R.id.nav_favorite -> {
//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            com.luteh.comicreader.R.id.nav_recent -> {
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
