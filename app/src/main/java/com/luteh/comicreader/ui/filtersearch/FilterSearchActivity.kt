package com.luteh.comicreader.ui.filtersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.luteh.comicreader.R
import com.luteh.comicreader.ui.adapter.MyComicAdapter
import com.luteh.comicreader.common.Common
import com.luteh.comicreader.model.Comic
import kotlinx.android.synthetic.main.activity_filter_search.*
import kotlin.collections.ArrayList

class FilterSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_search)

        bottom_app_bar.inflateMenu(R.menu.main_menu)
        bottom_app_bar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_filter -> showOptionsDialog()
                R.id.action_search -> showSearchDialog()
            }
            true
        }

        rv_filter_search.setHasFixedSize(true)
        rv_filter_search.layoutManager = GridLayoutManager(this, 2)
    }

    private fun showSearchDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Enter manga title")

        val inflater = this.layoutInflater
        val search_layout = inflater.inflate(R.layout.dialog_search, null)

        val edt_search = search_layout.findViewById<View>(R.id.edt_search) as EditText

        alertDialog.setView(search_layout)
        alertDialog.setNegativeButton("CANCEL") { dialog, which -> dialog.dismiss() }
        alertDialog.setPositiveButton("SEARCH") { dialog, which -> fetchSearchComic(edt_search.text.toString()) }

        alertDialog.show()
    }

    private fun fetchSearchComic(search: String) {
        val comicSearched = ArrayList<Comic>()
        for (comic in Common.comicList) {
            if (comic.Name != null) {
                if (comic.Name!!.contains(search, true))
                    comicSearched.add(comic)
            }
        }

        if (comicSearched.size > 0) {
            rv_filter_search.adapter = MyComicAdapter(this, comicSearched)
        } else {
            Toast.makeText(this, "No Result", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showOptionsDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Select Category")

        val inflater = this.layoutInflater
        val filter_layout = inflater.inflate(R.layout.dialog_filter, null)


        val chip_group = filter_layout.findViewById<View>(R.id.chip_group) as ChipGroup

        val autoCompleteTextView = filter_layout.findViewById<View>(R.id.edt_category) as AutoCompleteTextView
        autoCompleteTextView.threshold = 3
        autoCompleteTextView.setAdapter(ArrayAdapter(this, android.R.layout.select_dialog_item, Common.categories))

        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            // Clear text
            autoCompleteTextView.setText("")

            // Add Chip
            val chip = inflater.inflate(R.layout.chip_item, null, false) as Chip
            chip.text = (view as TextView).text
            chip.setOnCloseIconClickListener { view -> chip_group.removeView(view) }
            chip_group.addView(chip)
        }

        alertDialog.setView(filter_layout)
        alertDialog.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        alertDialog.setPositiveButton("Filter") { dialog, which ->
            val filter_key = ArrayList<String>()
            val filter_query = StringBuilder("")
            for (j in 0 until chip_group.childCount) {
                val chip = chip_group.getChildAt(j) as Chip
                filter_key.add(chip.text.toString())
            }
            // After get all category key, just sort it
            filter_key.sort()
            // Convert to string
            for (key in filter_key) {
                filter_query.append(key).append(", ")
            }
            // Remove last ", "
            filter_query.setLength(filter_query.length - 1)
            // Get all comic with category search
            fetchFilterCategory(filter_query.toString())
        }

        alertDialog.show()
    }

    private fun fetchFilterCategory(query: String) {
        val comicFiltered = ArrayList<Comic>()
        for (comic in Common.comicList) {
            if (comic.Category != null) {
                if (comic.Category!!.contains(query, true))
                    comicFiltered.add(comic)
            }
        }

        if (comicFiltered.size > 0) {
            rv_filter_search.adapter = MyComicAdapter(this, comicFiltered)
        } else {
            Toast.makeText(this, "No Result", Toast.LENGTH_SHORT).show()
        }
    }
}
