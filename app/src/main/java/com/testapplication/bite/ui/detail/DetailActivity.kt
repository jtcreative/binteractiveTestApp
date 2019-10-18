package com.testapplication.bite.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.testapplication.bite.R
import com.testapplication.bite.network.model.Book

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailBookFragment.newInstance(intent.getParcelableExtra("book") as Book))
                .commitNow()
        }
    }

    companion object {

        const val ARG_BOOK = "book"

        @JvmStatic
        fun newIntent(context : Context, book: Book): Intent {
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARG_BOOK, book)
            return intent
        }
    }
}
