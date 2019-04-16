package com.luteh.mangareader.ui.fragment.discover.adapter

import androidx.recyclerview.widget.DiffUtil
import com.luteh.mangareader.data.model.api.Manga

/**
 * Created by Luthfan Maftuh on 19/03/2019.
 * Email luthfanmaftuh@gmail.com
 */
class MangaDiffCallback(val oldList: MutableList<Manga>, val newList: MutableList<Manga>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id


    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    /*override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val new = newList[newItemPosition]
        val old = oldList[oldItemPosition]
        val isIdTheSame = new.id == old.id
        val isAliasTheSame = new.alias == old.alias
        val isCategoryTheSame = new.category == old.category
        val isHitsTheSame = new.hits == old.hits
        val isLastChapterDateTheSame = new.lastChapterDate == old.lastChapterDate
        val isStatusTheSame = new.status == old.status
        val isTitleTheSame = new.title == old.title
        val isImageTheSame = new.image == old.image
        return isIdTheSame && isAliasTheSame && isCategoryTheSame && isHitsTheSame &&
                isLastChapterDateTheSame && isStatusTheSame && isTitleTheSame &&
                isImageTheSame
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val new = newList[newItemPosition]
        val old = oldList[oldItemPosition]
        val set = mutableSetOf<String>()
        val isIdTheSame = new.id == old.id
        val isAliasTheSame = new.alias == old.alias
        val isCategoryTheSame = new.category == old.category
        val isHitsTheSame = new.hits == old.hits
        val isLastChapterDateTheSame = new.lastChapterDate == old.lastChapterDate
        val isStatusTheSame = new.status == old.status
        val isTitleTheSame = new.title == old.title
        val isImageTheSame = new.image == old.image
        if (isIdTheSame.not()) {
            set.add(ID)
        }
        if (isAliasTheSame.not()) {
            set.add(ALIAS)
        }
        if (isCategoryTheSame.not()) {
            set.add(CATEGORY)
        }
        if (isImageTheSame.not())
            set.add(IMAGE)
        if (isHitsTheSame.not())
            set.add(HITS)
        if (isLastChapterDateTheSame.not())
            set.add(LASTCHAPTERDATE)
        if (isStatusTheSame.not())
            set.add(STATUS)
        if (isTitleTheSame.not())
            set.add(TITLE)
        return set
    }*/

    companion object {
        const val ID = "ID"
        const val ALIAS = "ALIAS"
        const val CATEGORY = "CATEGORY"
        const val HITS = "HITS"
        const val IMAGE = "IMAGE"
        const val LASTCHAPTERDATE = "LASTCHAPTERDATE"
        const val STATUS = "STATUS"
        const val TITLE = "TITLE"
    }
}