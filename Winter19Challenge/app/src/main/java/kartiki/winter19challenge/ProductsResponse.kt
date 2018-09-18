package kartiki.winter19challenge

import com.google.gson.annotations.SerializedName

/**
 * Created by Kartiki on 2018-09-17.
 */
class ProductsResponse(@SerializedName("products") val products: List<Product>)