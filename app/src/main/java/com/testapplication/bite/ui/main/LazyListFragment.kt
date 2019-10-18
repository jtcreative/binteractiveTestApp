package com.testapplication.bite.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.testapplication.bite.R
import com.testapplication.bite.adapter.LazyRecyclerViewAdapter

import com.testapplication.bite.network.model.Book
import com.testapplication.bite.network.model.Response
import com.testapplication.bite.util.LazyLoaderHelper
import kotlinx.android.synthetic.main.fragment_book_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [LazyListFragment.OnListFragmentInteractionListener] interface.
 */
class LazyListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var totalItems = 0
    private var currentItemTotal = 0
    private var threshold = 1.0

    private var listener: OnListFragmentInteractionListener? = null
    private var listView: RecyclerView? = null
    private var isLoading:Boolean = false
        get
        set(value) {
            if (value) {
                if(progressBar != null) progressBar.visibility = VISIBLE
                if(progressText != null) progressText.visibility = VISIBLE
            } else {
                if(progressBar != null) progressBar.visibility = INVISIBLE
                if(progressText != null) progressText.visibility = INVISIBLE
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            isLoading = it.getBoolean(ARG_IS_LOADING)
            totalItems = it.getInt(ARG_TOTAL_ITEMS)
            currentItemTotal = it.getInt(ARG_CURRENT_ITEM_TOTAL)
            threshold = it.getDouble(ARG_THRESHOLD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)
        listView = view.findViewById(R.id.list)

        // Set the adapter
        if (listView != null) {
            with(listView!!) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                val scrollListener = object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (currentItemTotal == totalItems) {
                            recyclerView.removeOnScrollListener(this)
                        } else if (!isLoading
                            && !recyclerView.canScrollVertically(1)
                            && LazyLoaderHelper.isVslidOffset(currentItemTotal, 20)){
                            listener?.onNewItemsRequested(currentItemTotal)
                            isLoading = true
                        }
                    }
                }

                listView?.addOnScrollListener(scrollListener)
                adapter = LazyRecyclerViewAdapter(
                    mutableListOf(),
                    listener
                )
            }

            isLoading = true
            listener?.onNewItemsRequested(0)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun addItemsToList(items: Response<Book>) {
        if (totalItems == 0) {
            totalItems = items.numResults
        }

        (listView?.adapter as LazyRecyclerViewAdapter).addItemsToAdapter(items.results)
        currentItemTotal += items.results.size
        isLoading = false
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onItemSelected(item: Book?)

        fun onNewItemsRequested(atCurrentIndex:Int)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_IS_LOADING = "is-loading"
        const val ARG_TOTAL_ITEMS = "total-items"
        const val ARG_CURRENT_ITEM_TOTAL = "current-item-total"
        const val ARG_THRESHOLD = "threshold"

        @JvmStatic
        fun newInstance(columnCount: Int, totalItems:Int, threshold:Double) =
            LazyListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putBoolean(ARG_IS_LOADING, true)
                    putInt(ARG_TOTAL_ITEMS, totalItems)
                    putInt(ARG_CURRENT_ITEM_TOTAL, 0)
                    putDouble(ARG_THRESHOLD, threshold)
                }
            }
    }
}
