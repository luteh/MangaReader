package com.luteh.comicreader.ui.main

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.luteh.comicreader.api.ApiServiceInterface
import com.luteh.comicreader.common.Constants
import com.luteh.comicreader.model.Comic
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

    private var db = FirebaseDatabase.getInstance()

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
        val subscribe = api.getMangaList(0).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ mangaList ->
                Log.d(TAG, "onSuccess: ${mangaList.manga[1].title}")
                view.onSuccessLoadMangaListData(mangaList.manga)
            },
                { error -> Log.e(TAG, "onError: $error") })

        subscriptions.add(subscribe)
    }

    override fun loadBannerData() {
        subscriptions.add(
            getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<String>>() {
                    override fun onSuccess(t: ArrayList<String>) {
                        Log.d(TAG, "onSuccess: ${Thread.currentThread()}")
                        view.onBannerLoadDoneListener(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ${Thread.currentThread()}")
                        view.showErrorMessage(e.message!!)
                    }
                })
        )
    }

    /**
     * Create Observable to get Banner data from firebase database
     */
    private fun getBannerData(): Single<ArrayList<String>> {
        return Single.create { emitter ->
            Log.d(TAG, "Observable: ${Thread.currentThread()}")

            db.getReference(Constants.ARG_BANNERS).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    emitter.onError(p0.toException())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val banner_list = ArrayList<String>()
                    for (banner in p0.children) {
                        val image = banner.getValue(String::class.java)
                        banner_list.add(image!!)
                    }

                    emitter.onSuccess(banner_list)
                }
            })
        }
    }

    override fun loadComicData() {
        subscriptions.add(
            getComicData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MutableList<Comic>>() {
                    override fun onSuccess(t: MutableList<Comic>) {
                        Log.d(TAG, "onSuccess: ${Thread.currentThread()}")
                        view.onComicLoadDoneListener(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ${Thread.currentThread()}")
                        view.showErrorMessage(e.message!!)
                    }
                })
        )
    }

    /**
     * Create Observable to get Comic data from firebase database
     * Emits datasnapshot list
     */
    private fun getComicData(): Single<MutableList<Comic>> {
        return Single.create { emitter ->
            Log.d(TAG, "Observable: ${Thread.currentThread()}")
            db.getReference(Constants.ARG_COMIC).addListenerForSingleValueEvent(object : ValueEventListener {
                var comic_load: MutableList<Comic> = ArrayList()

                override fun onCancelled(p0: DatabaseError) {
                    emitter.onError(p0.toException())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (comicSnapshot in p0.children) {
                        val comic = comicSnapshot.getValue(Comic::class.java)
                        comic_load.add(comic!!)
                    }

                    emitter.onSuccess(comic_load)
                }
            })
        }
    }
}