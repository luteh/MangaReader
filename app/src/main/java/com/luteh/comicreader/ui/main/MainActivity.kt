package com.luteh.comicreader.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.luteh.comicreader.R
import com.luteh.comicreader.common.base.BaseActivity
import com.luteh.comicreader.common.Common
import com.luteh.comicreader.di.component.DaggerActivityComponent
import com.luteh.comicreader.di.module.ActivityModule
import com.luteh.comicreader.model.Comic
import com.luteh.comicreader.service.PicassoImageLoadingService
import com.luteh.comicreader.ui.adapter.MyComicAdapter
import com.luteh.comicreader.ui.filtersearch.FilterSearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)
        initView()
    }

    override fun injectDependencyIfNeed() {
        val mainComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        mainComponent.inject(this)
    }

    private fun initView() {
        initRecycler()

        initSwipeRefresh()

        Slider.init(PicassoImageLoadingService())

        btn_show_filter_search.setOnClickListener {
            startActivity(Intent(this, FilterSearchActivity::class.java))
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initSwipeRefresh() {
        //        First, load banner and comic
        swipe_to_refresh.setColorSchemeColors(
            R.color.colorPrimary,
            R.color.colorPrimaryDark
        )
        swipe_to_refresh.setOnRefreshListener {
            presenter.loadBannerData()
            presenter.loadComicData()
        }
        swipe_to_refresh.post {
            presenter.loadBannerData()
            presenter.loadComicData()
        }
    }

    private fun initRecycler() {
        rv_comic.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
        }
    }


    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBannerLoadDoneListener(bannerList: ArrayList<String>) {
        slider.setAdapter(MySliderAdapter(bannerList))
    }

    override fun onComicLoadDoneListener(comicList: MutableList<Comic>) {
        Common.comicList = comicList

        rv_comic.adapter = MyComicAdapter(this, Common.comicList)
        tv_comic.text = StringBuilder("NEW COMIC (")
            .append(Common.comicList.size)
            .append(")")

        if (swipe_to_refresh.isRefreshing) {
            swipe_to_refresh.isRefreshing = false
        }
    }
}
