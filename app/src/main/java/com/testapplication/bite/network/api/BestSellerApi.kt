package com.testapplication.bite.network.api

import com.testapplication.bite.network.api.RestApi
import com.testapplication.bite.network.factory.ResponseModelFactory
import com.testapplication.bite.network.model.Book
import com.testapplication.bite.network.model.Response

open class BestSellerApi(override val modelFactory: ResponseModelFactory = ResponseModelFactory(), override val endpoint: String = "/lists/best-sellers/history.json") :
    RestApi<Response<Book>>(baseUrl = "https://api.nytimes.com/svc/books/v3") {

    open fun getBestSellers(atOffset:Int = 0): Response<Book>? {
        val query = "offset=".plus(atOffset)
        return get(query)
    }

}