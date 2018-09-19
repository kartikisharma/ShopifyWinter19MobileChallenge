package kartiki.winter19challenge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Kartiki on 2018-09-17.
 */

interface TagItemListener {
    fun onTagClicked(tag: String)
}

class TagsAdapter(private val tags: List<String>, private val tagItemListener: TagItemListener)
    : RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_card, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val tag = tags[position]
        holder.setTagName(tag)
        holder.itemView.setOnClickListener {
            tagItemListener.onTagClicked(tag)
        }
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.tag_title)
        lateinit var textView: TextView

        init {
            ButterKnife.bind(this, view)
        }

        fun setTagName(tag: String) {
            textView.text = tag
        }
    }
}