package com.luteh.comicreader.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luteh.comicreader.R
import com.luteh.comicreader.common.base.BaseActivity
import com.luteh.comicreader.ui.discover.DiscoverFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    override fun injectDependencyIfNeed() {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_discover -> {
//                Toast.makeText(, "Clicked", Toast.LENGTH_SHORT).show();
                replaceFragment(DiscoverFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorite -> {
//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_recent -> {
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bnv_home.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onInit() {
        replaceFragment(DiscoverFragment())
    }
}
