package kartiki.winter19challenge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import kartiki.winter19challenge.models.Product
import android.support.v7.widget.DividerItemDecoration


/**
 * Created by Kartiki on 2018-09-17.
 */
class ProductsActivity : AppCompatActivity() {
    @BindView(R.id.recycler_view)
    lateinit var productsRecyclerView: RecyclerView

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        supportActionBar?.let {
            it.title = resources.getString(R.string.product_activity_title)
            it.setDisplayHomeAsUpEnabled(true)
        }

        productsRecyclerView.setHasFixedSize(true)
        productsRecyclerView.layoutManager = LinearLayoutManager(this)
        productsRecyclerView.addItemDecoration(DividerItemDecoration(productsRecyclerView.context,
                DividerItemDecoration.VERTICAL));
        productsRecyclerView.adapter = ProductsAdapter(intent.getParcelableArrayListExtra<Product>(MainActivity.PRODUCTS_KEY))
        productsRecyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}