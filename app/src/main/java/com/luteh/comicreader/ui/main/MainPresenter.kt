package com.luteh.comicreader.ui.main

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.luteh.comicreader.common.Constant
import com.luteh.comicreader.model.Comic

/**
 * Created by Luthfan Maftuh on 12/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
//    private lateinit var view: MainContract.View

    private var db = FirebaseDatabase.getInstance()

    override fun attach(view: MainContract.View) {
//        this.view = view

    }

    override fun loadBannerData() {
        db.getReference(Constant.ARG_BANNERS).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.showErrorMessage(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val banner_list = ArrayList<String>()
                for (banner in p0.children) {
                    val image = banner.getValue(String::class.java)
                    banner_list.add(image!!)
                }

                view.onBannerLoadDoneListener(banner_list)
            }

        })
    }

    override fun loadComicData() {
        db.getReference(Constant.ARG_COMIC).addListenerForSingleValueEvent(object : ValueEventListener {
            var comic_load: MutableList<Comic> = ArrayList()

            override fun onCancelled(p0: DatabaseError) {
                view.showErrorMessage(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (comicSnapshot in p0.children) {
                    val comic = comicSnapshot.getValue(Comic::class.java)
                    comic_load.add(comic!!)
                }

                view.onComicLoadDoneListener(comic_load)
            }
        })
    }
}