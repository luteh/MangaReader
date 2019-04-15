package com.luteh.mangareader.ui.fragment.discover


import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Fragment
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager

import com.luteh.mangareader.R
import com.luteh.mangareader.common.Common
import com.luteh.mangareader.common.base.BaseFragment
import com.luteh.mangareader.di.component.DaggerFragmentComponent
import com.luteh.mangareader.di.module.FragmentModule
import com.luteh.mangareader.data.model.Manga
import com.luteh.mangareader.ui.fragment.discover.adapter.MangaAdapter
import com.luteh.mangareader.ui.main.DiscoverContract
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.discover_dialog_sort_by.view.*
import kotlinx.android.synthetic.main.discover_fragment.*
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class DiscoverFragment : BaseFragment(), DiscoverContract.View {

    @Inject
    lateinit var presenter: DiscoverContract.Presenter

    private lateinit var dialog: AlertDialog

    private lateinit var adapter: MangaAdapter
//    private var adapter: RecyclerView.Adapter<*>? = null

    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.discover_fragment, container, false)
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
        initRecycler(3)

        initSwipeRefresh()

        btn_discover_sort_by.setOnClickListener { showSortByDialog() }

//        Slider.init(PicassoImageLoadingService())

//        presenter.loadMangaListData()
        pb_discover.visibility = View.VISIBLE
        rv_discover.visibility = View.GONE
    }

    /**
     * To show sort by dialog
     */
    private fun showSortByDialog() {
        if (!::dialog.isInitialized) {
            val view = LayoutInflater.from(context).inflate(R.layout.discover_dialog_sort_by, null)
            dialog = AlertDialog.Builder(context!!)
                .setTitle(R.string.title_dialog_sort_by)
                .setView(view)
                .create()

            // Handle radio group listener
            view.rb_discover_dialog_updated.isChecked = true
            view.rg_discover_dialog_sort_by.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.rb_discover_dialog_name -> {
                        sortAdapter(0, view.rb_discover_dialog_name.text)
                        view.rb_discover_dialog_name.isChecked = true
                        dialog.dismiss()
                    }
                    R.id.rb_discover_dialog_popular -> {
                        sortAdapter(1, view.rb_discover_dialog_popular.text)
                        view.rb_discover_dialog_popular.isChecked = true
                        dialog.dismiss()
                    }
                    R.id.rb_discover_dialog_updated -> {
                        sortAdapter(2, view.rb_discover_dialog_updated.text)
                        view.rb_discover_dialog_updated.isChecked = true
                        dialog.dismiss()
                    }
                }
            }
        }

        dialog.show()
    }

    /**
     * To sorting adapter items
     * @param   sortOption  Sorting options that selected by user
     */
    private fun sortAdapter(sortOption: Int, optionText: CharSequence) {
        tv_discover_sort_by.text = optionText

        Common.mangaList.sortWith(Comparator { o1, o2 ->
            when (sortOption) {
                1 -> o2.hits.compareTo(o1.hits)
                2 -> o2.lastChapterDate.compareTo(o1.lastChapterDate)
                else -> o1.title.compareTo(o2.title)
            }
        })

//        Common.mangaList.sortByDescending { it.hits }
//         TODO: 18/03/2019 Implement DiffUtils to changes adapter value instead of re-init adapter
//        rv_discover.adapter = MangaAdapter(this.activity!!, Common.mangaList)

        /*val flowable = Flowable.just(Common.mangaList)
            .subscribeOn(Schedulers.io())

        disposable = adapter.setDataSource(flowable)
        */
//        adapter.updateItem(Common.mangaList)
        adapter.notifyDataSetChanged()

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

    private fun initRecycler(spanCount: Int) {
        rv_discover.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, spanCount)
        }
    }


    override fun showErrorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun onSuccessLoadMangaListData(mangaList: MutableList<Manga>) {
        pb_discover.visibility = View.GONE
        rv_discover.visibility = View.VISIBLE

        Common.mangaList = mangaList

//        sortAdapter(2, resources.getText(R.string.label_sort_by_updated))

        tv_discover_sort_by.text = resources.getText(R.string.label_sort_by_updated)

        Common.mangaList.sortWith(Comparator { o1, o2 ->
            o2.lastChapterDate.compareTo(o1.lastChapterDate)
        })

        adapter = MangaAdapter(this.activity!!, Common.mangaList)
        rv_discover.adapter = adapter

        if (swipe_to_refresh.isRefreshing) {
            swipe_to_refresh.isRefreshing = false
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Handle items count if screen orientation changed
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            initRecycler(R.integer.grid_span_count_landscape)
        else
            initRecycler(R.integer.grid_span_count_portrait)
    }

    override fun onDestroyView() {
        if (disposable?.isDisposed ?: false) {
            disposable?.dispose()
        }
        super.onDestroyView()
    }
}
