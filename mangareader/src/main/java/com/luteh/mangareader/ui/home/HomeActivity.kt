package com.luteh.mangareader.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luteh.mangareader.R
import com.luteh.mangareader.common.base.BaseActivity
import com.luteh.mangareader.ui.discover.DiscoverFragment
import kotlinx.android.synthetic.main.home_activity.*
import com.luteh.mangareader.common.utils.BottomNavigationBehavior
import androidx.coordinatorlayout.widget.CoordinatorLayout


class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.luteh.mangareader.R.layout.home_activity)
        setSupportActionBar(toolbar_home)
    }

    override fun injectDependencyIfNeed() {

    }

    override fun onInit() {
        // Init fragment
        replaceFragment(DiscoverFragment(), R.string.title_fragment_discover)

        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        bnv_home.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val layoutParams = bnv_home.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                com.luteh.mangareader.R.id.nav_discover -> {
//                Toast.makeText(, "Clicked", Toast.LENGTH_SHORT).show();
                    replaceFragment(DiscoverFragment(), R.string.title_fragment_discover)
                    return@OnNavigationItemSelectedListener true
                }
                com.luteh.mangareader.R.id.nav_favorite -> {
//                message.setText(R.string.title_dashboard)
                    return@OnNavigationItemSelectedListener true
                }
                com.luteh.mangareader.R.id.nav_recent -> {
//                message.setText(R.string.title_notifications)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
