package com.luteh.mangareader.ui.main

import android.util.Log
import com.luteh.mangareader.api.ApiServiceInterface
import com.luteh.mangareader.common.Constants
import com.luteh.mangareader.model.Comic
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class DiscoverPresenter : DiscoverContract.Presenter {

    private val TAG = "DiscoverPresenter"

    private lateinit var view: DiscoverContract.View
    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()

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