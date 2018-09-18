package kartiki.winter19challenge

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kartiki on 2018-09-17.
 */

const val ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6"
interface APIService {
    @GET("admin/products.json")
    fun listProducts(@Query("page") page: Int,
                     @Query("access_token") accessToken: String = ACCESS_TOKEN): Call<ProductsResponse>
}