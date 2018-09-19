package kartiki.winter19challenge.models

import com.google.gson.annotations.SerializedName
import kartiki.winter19challenge.models.Product

/**
 * Created by Kartiki on 2018-09-17.
 */
class ProductsResponse(@SerializedName("products") val products: List<Product>)