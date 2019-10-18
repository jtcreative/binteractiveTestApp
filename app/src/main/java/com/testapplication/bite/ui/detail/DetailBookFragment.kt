package com.testapplication.bite.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.testapplication.bite.R
import com.testapplication.bite.network.model.Book
import kotlinx.android.synthetic.main.detail_book_fragment.*

class DetailBookFragment : Fragment() {

    private lateinit var viewModel: DetailBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailBookViewModel::class.java)
        arguments?.let {
            viewModel.book = it.getParcelable(DetailActivity.ARG_BOOK) as Book
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_book_fragment, container, false)
        var title = view.findViewById(R.id.title) as TextView
        var description = view.findViewById(R.id.description) as TextView
        var author = view.findViewById(R.id.author) as TextView
        var price = view.findViewById(R.id.price) as TextView

        title.text = "Title: " + viewModel.book?.title
        description.text = "Description: " + viewModel.book?.description
        author.text = "Author: " + viewModel.book?.author
        price.text = "Price: " + viewModel.book?.price

        return view
    }

    companion object {
        fun newInstance(book: Book) =
            DetailBookFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DetailActivity.ARG_BOOK, book)
                }
            }
    }

}
