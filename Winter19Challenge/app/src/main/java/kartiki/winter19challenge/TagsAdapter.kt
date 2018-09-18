package kartiki.winter19challenge

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Kartiki on 2018-09-17.
 */

interface TagItemListener {
    fun onTagClicked(tag: String)
}

class TagsAdapter(private val tags: List<String>, private val tagItemListener: TagItemListener) : RecyclerView.Adapter<TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = TextView(parent.context)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val tag = tags[position]
        holder.textView?.text = tag
        holder.itemView.setOnClickListener {
            tagItemListener.onTagClicked(tag)
        }
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}