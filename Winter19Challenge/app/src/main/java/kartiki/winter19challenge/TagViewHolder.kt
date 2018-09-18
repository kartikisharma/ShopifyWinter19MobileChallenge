package kartiki.winter19challenge

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView


/**
 * Created by Kartiki on 2018-09-17.
 */
class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView? = null

    init {
        textView = view as TextView
    }

    fun setTagName(tag: String) {
        textView?.text = tag
    }
}