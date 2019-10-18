package com.testapplication.bite.network

import android.os.AsyncTask
import com.testapplication.bite.network.api.BestSellerApi
import com.testapplication.bite.network.model.Book
import com.testapplication.bite.network.model.Response

class BestSellerAsyncTask(val taskResponder : BestSellerAsyncTaskResponder) : AsyncTask<Int, Response<Book>?, Response<Book>?>() {
     override fun doInBackground(vararg params: Int?): Response<Book>? {
         val bestSellerApi = BestSellerApi()
         val atOffset = params.get(0)!!
         val response = bestSellerApi.getBestSellers(atOffset = atOffset)
         return response
    }

    override fun onPostExecute(result: Response<Book>?) {
        super.onPostExecute(result)
        taskResponder.onCompleted(result)
    }
}

interface BestSellerAsyncTaskResponder {
    fun onCompleted(response:Response<Book>?)
}