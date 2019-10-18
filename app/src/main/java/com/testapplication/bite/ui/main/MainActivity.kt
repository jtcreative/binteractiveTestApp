package com.testapplication.bite.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.testapplication.bite.R
import com.testapplication.bite.network.BestSellerAsyncTask
import com.testapplication.bite.network.BestSellerAsyncTaskResponder
import com.testapplication.bite.network.model.Book
import com.testapplication.bite.network.model.Response
import com.testapplication.bite.ui.detail.DetailActivity

class MainActivity() : AppCompatActivity(), BestSellerAsyncTaskResponder, LazyListFragment.OnListFragmentInteractionListener {

    var lazyListFragment : LazyListFragment? = null
    var bsrTask : BestSellerAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            lazyListFragment = LazyListFragment.newInstance(1, 0, 0.8)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, lazyListFragment!!)
                .commitNow()
        }
    }


    override fun onCompleted(response: Response<Book>?) {
        if (response != null) {
            lazyListFragment?.addItemsToList(response!!)
            bsrTask = null
        }
    }

    override fun onItemSelected(item: Book?) {
        if(item != null) {
            var intent = DetailActivity.newIntent(this, item!!)
            startActivity(intent)
        }
    }

    override fun onNewItemsRequested(atCurrentIndex: Int) {
        if(bsrTask == null) {
            bsrTask = BestSellerAsyncTask(this)
            bsrTask?.execute(atCurrentIndex)
        }
    }

}
