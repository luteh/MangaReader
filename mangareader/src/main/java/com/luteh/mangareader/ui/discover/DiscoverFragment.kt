package com.luteh.mangareader.ui.discover


import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager

import com.luteh.mangareader.R
import com.luteh.mangareader.common.base.BaseFragment
import com.luteh.mangareader.di.component.DaggerFragmentComponent
import com.luteh.mangareader.di.module.FragmentModule
import com.luteh.mangareader.model.Comic
import com.luteh.mangareader.model.Manga
import com.luteh.mangareader.ui.adapter.MangaAdapter
import com.luteh.mangareader.ui.main.DiscoverContract
import kotlinx.android.synthetic.main.fragment_discover.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class DiscoverFragment : BaseFragment(), DiscoverContract.View {

    @Inject
    lateinit var presenter: DiscoverContract.Presenter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun injectDependency() {
        val discoverComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        discoverComponent.inject(this)
    }

    override fun onInit() {
        presenter.attach(this)
        initView()
    }

    private fun initView() {
        initRecycler()

        initSwipeRefresh()

//        Slider.init(PicassoImageLoadingService())

//        presenter.loadMangaListData()
        pb_discover.visibility = View.VISIBLE
    }

    @SuppressLint("ResourceAsColor")
    private fun initSwipeRefresh() {
        //        First, load banner and comic
        swipe_to_refresh.setColorSchemeColors(
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        )
        swipe_to_refresh.setOnRefreshListener {
            //            presenter.loadBannerData()
//            presenter.loadComicData()
            presenter.loadMangaListData()
        }
        swipe_to_refresh.post {
            //            presenter.loadBannerData()
//            presenter.loadComicData()
            presenter.loadMangaListData()
        }
    }

    private fun initRecycler() {
        rv_discover.apply {
            visibility = View.GONE
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
        }
    }


    override fun showErrorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun onSuccessLoadMangaListData(mangaList: List<Manga>) {
        pb_discover.visibility = View.GONE
        rv_discover.visibility = View.VISIBLE

        rv_discover.adapter = MangaAdapter(this.activity!!, mangaList)

        if (swipe_to_refresh.isRefreshing) {
            swipe_to_refresh.isRefreshing = false
        }
    }
}