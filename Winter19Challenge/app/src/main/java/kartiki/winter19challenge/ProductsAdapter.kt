package kartiki.winter19challenge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import kartiki.winter19challenge.models.Product


/**
 * Created by Kartiki on 2018-09-17.
 */

class ProductsAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = products[position]

        holder.setImage(currentProduct.productImage.imageUrl ?: "")
        holder.setTitle(currentProduct.title)
        holder.setDescription(currentProduct.description)
        holder.setQuantity(currentProduct.getQuantity())
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

        @BindView(R.id.product_inventory)
        lateinit var productInventory: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun setImage(imageUrl: String) {
            Picasso.with(imageView.context)
                    .load(imageUrl)
                    .placeholder(android.R.color.darker_gray)
                    .resize(imageView.maxWidth, imageView.maxHeight)
                    .into(imageView)
        }

        fun setTitle(text: String) {
            titleTextView.text = text
        }

        fun setDescription(text: String) {
            descriptionTextView.text = text
        }

        fun setQuantity(amount: Int) {
            productInventory.text = String.format(productInventory.context.getString(R.string.product_quantity_availability), amount)
        }
    }
}