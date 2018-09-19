package kartiki.winter19challenge

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import kartiki.winter19challenge.models.Product
import kartiki.winter19challenge.models.ProductsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.support.v7.widget.DividerItemDecoration

interface TagListView {
    fun onDataReady()
    fun onAPIFailure()
}

class MainActivity : AppCompatActivity(), TagItemListener, TagListView {
    companion object {
        const val PRODUCTS_KEY = "PRODUCTS_KEY"
    }

    val map = HashMap<String, ArrayList<Product>>()

    @BindView(R.id.recycler_view)
    lateinit var tagsRecyclerView: RecyclerView

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://shopicruit.myshopify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val apiService = retrofit.create<APIService>(APIService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        supportActionBar?.title = resources.getString(R.string.tags_activity_title)

        //initialize tags adapter
        tagsRecyclerView.setHasFixedSize(true)
        tagsRecyclerView.layoutManager = LinearLayoutManager(this)
        tagsRecyclerView.addItemDecoration(DividerItemDecoration(tagsRecyclerView.context,
                DividerItemDecoration.VERTICAL))

        fetchTags()
    }

    private fun fetchTags() {
        val call = apiService.listProducts(1)
        call.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>?, response: Response<ProductsResponse>?) {
                response?.let {
                    if (it.isSuccessful) {
                        it.body()?.products?.let {
                            for (item in it) {
                                val tags = item.getTagList()
                                for (tag in tags) {
                                    if (map.contains(tag)) {
                                        val arrayList = map[tag]
                                        arrayList?.add(item)
                                        map.put(tag, arrayList ?: ArrayList())
                                    } else {
                                        map.put(tag, arrayListOf(item))
                                    }
                                } // for
                            } // for
                        } // elvis operator
                        onDataReady()
                    } //if
                    else {
                        onAPIFailure()
                    }
                } ?: onAPIFailure()
            }

            override fun onFailure(call: Call<ProductsResponse>?, t: Throwable?) {
                onAPIFailure()
            }
        })

    }

    override fun onAPIFailure() {
        Toast.makeText(this@MainActivity, "Failed to fetch data. Please try again later.", Toast.LENGTH_SHORT).show()
        onDataReady()
    }

    override fun onDataReady() {
        this.runOnUiThread {
            if (map.isNotEmpty()) {
                tagsRecyclerView.adapter = TagsAdapter(map.keys.toList(), this)
            } else {
                tagsRecyclerView.adapter = null
            }

            progressBar.visibility = View.GONE
            tagsRecyclerView.visibility = View.VISIBLE
        }
    }


    // start TagItemListener
    override fun onTagClicked(tag: String) {
        val products = map[tag]
        products?.let {
            val intent = Intent(this, ProductsActivity::class.java)
            intent.putParcelableArrayListExtra(PRODUCTS_KEY, it)
            startActivity(intent)
        }
    }
    // endregion
}
