package com.luteh.mangareader.ui.main

import android.util.Log
import com.luteh.mangareader.common.base.BaseViewModel
import com.luteh.mangareader.data.remote.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class DiscoverPresenter : DiscoverContract.Presenter, BaseViewModel() {

    private val TAG = "DiscoverPresenter"

    private lateinit var view: DiscoverContract.View
    private val subscriptions = CompositeDisposable()

    @Inject
    lateinit var api: ApiServiceInterface

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DiscoverContract.View) {
        this.view = view

    }

    /**
     * Load manga list data from API
     */
    override fun loadMangaListData() {
        val subscribe = api.getMangaList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mangaList ->
                Log.d(TAG, "Manga size: ${mangaList.manga.size}")
                view.onSuccessLoadMangaListData(mangaList.manga)
            },
                { error -> Log.e(TAG, "onError: $error") })

        subscriptions.add(subscribe)
    }
}