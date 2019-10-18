package com.testapplication.bite.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.testapplication.bite.R


import com.testapplication.bite.ui.main.LazyListFragment.OnListFragmentInteractionListener
import com.testapplication.bite.network.model.Book

import kotlinx.android.synthetic.main.main_fragment_book.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class LazyRecyclerViewAdapter(
    private val mBooks: MutableList<Book>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<LazyRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Book
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onItemSelected(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_fragment_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mBooks[position]
        holder.mIdView.text = position.toString()
        holder.mContentView.text = item.title

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mBooks.size

    fun addItemsToAdapter(items:List<Book>) {
        val startPosition = mBooks.size - 1
        mBooks.addAll(items)
        this.notifyItemRangeChanged(startPosition, items.size)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
