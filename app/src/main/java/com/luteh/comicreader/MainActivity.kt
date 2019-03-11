package com.luteh.comicreader

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.luteh.comicreader.`interface`.IBannerLoadDoneListener
import com.luteh.comicreader.`interface`.IComicLoadDoneListener
import com.luteh.comicreader.adapter.MyComicAdapter
import com.luteh.comicreader.adapter.MySliderAdapter
import com.luteh.comicreader.common.Common
import com.luteh.comicreader.model.Comic
import com.luteh.comicreader.service.PicassoImageLoadingService
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider

class MainActivity : AppCompatActivity(), IBannerLoadDoneListener, IComicLoadDoneListener {
    override fun onComicLoadDoneListener(comicList: List<Comic>) {
        alertDialog.dismiss()

        Common.comicList = comicList

        rv_comic.adapter = MyComicAdapter(this, comicList)
        tv_comic.text = StringBuilder("NEW COMIC (")
            .append(comicList.size)
            .append(")")

        if (swipe_to_refresh.isRefreshing) {
            swipe_to_refresh.isRefreshing = false
        }
    }

    override fun onBannerLoadDoneListener(banners: List<String>) {
        slider.setAdapter(MySliderAdapter(banners))
    }

    //    Database
    lateinit var banners_ref: DatabaseReference
    lateinit var comic_ref: DatabaseReference

    //    Listener
    lateinit var iBannerLoadDoneListener: IBannerLoadDoneListener
    lateinit var iComicLoadDoneListener: IComicLoadDoneListener

    lateinit var alertDialog: android.app.AlertDialog

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Init DB
        banners_ref = FirebaseDatabase.getInstance().getReference("Banners")
        comic_ref = FirebaseDatabase.getInstance().getReference("Comic")

//        Init listener
        iBannerLoadDoneListener = this
        iComicLoadDoneListener = this

//        Init dialog
        alertDialog = SpotsDialog.Builder().setContext(this@MainActivity)
            .setCancelable(false)
            .setMessage("Please wait...")
            .build()

//        First, load banner and comic
        swipe_to_refresh.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark)
        swipe_to_refresh.setOnRefreshListener {
            loadBanners()
            loadComics()
        }
        swipe_to_refresh.post {
            loadBanners()
            loadComics()
        }

        Slider.init(PicassoImageLoadingService())

        rv_comic.setHasFixedSize(true)
        rv_comic.layoutManager = GridLayoutManager(this, 2)

        btn_show_filter_search.setOnClickListener {
            startActivity(Intent(this, FilterSearchActivity::class.java))
        }
    }

    private fun loadComics() {
        alertDialog.show()

        comic_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            var comic_load: MutableList<Comic> = ArrayList()

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@MainActivity, "" + p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (comicSnapshot in p0.children) {
                    val comic = comicSnapshot.getValue(Comic::class.java)
                    comic_load.add(comic!!)
                }

                iComicLoadDoneListener.onComicLoadDoneListener(comic_load)
            }
        })
    }

    private fun loadBanners() {
        banners_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@MainActivity, "" + p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                val banner_list = ArrayList<String>()
                for (banner in p0.children) {
                    val image = banner.getValue(String::class.java)
                    banner_list.add(image!!)
                }

                iBannerLoadDoneListener.onBannerLoadDoneListener(banner_list)
            }

        })
    }
}
