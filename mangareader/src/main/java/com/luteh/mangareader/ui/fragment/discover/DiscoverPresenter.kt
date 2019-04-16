package com.luteh.mangareader.ui.main

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
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
class DiscoverPresenter : BaseViewModel(), DiscoverContract.Presenter {

    private val TAG = "DiscoverPresenter"

    @Inject
    lateinit var api: ApiServiceInterface

    private lateinit var view: DiscoverContract.View
    private val subscriptions = CompositeDisposable()

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DiscoverContract.View) {
        this.view = view

    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    /**
     * Load manga list data from API
     */
    override fun loadMangaListData() {
        subscriptions.add(
            api.getMangaList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.onStartLoading() }
                .doOnTerminate { view.onFinishLoading() }
                .subscribe({ mangaList ->
                    Log.d(TAG, "Manga size: ${mangaList.manga.size}")
                    view.onSuccessLoadMangaListData(mangaList.manga)
                },
                    { error ->
                        Log.e(TAG, "onError: $error")
                    })
        )
    }
}