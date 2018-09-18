package kartiki.winter19challenge

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.internal.Utils
import com.squareup.picasso.Picasso
import android.util.DisplayMetrics


/**
 * Created by Kartiki on 2018-09-17.
 */

//fun dpToPixel(dp: Float, context: Context): Float {
//    val metrics = context.resources.displayMetrics
//    return dp * (metrics.densityDpi / 160f)
//}

class ProductsAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = products.get(position)

        Picasso.with(holder.imageView.context)
                .load(currentProduct.productImage.imageUrl)
                .placeholder(android.R.color.darker_gray)
                .resize(holder.imageView.maxWidth, holder.imageView.maxHeight)
                .into(holder.imageView)

        holder.titleTextView.text = currentProduct.title
        holder.descriptionTextView.text = currentProduct.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.product_card, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.product_title)
        lateinit var titleTextView: TextView

        @BindView(R.id.product_description)
        lateinit var descriptionTextView: TextView

        @BindView(R.id.product_image)
        lateinit var imageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}