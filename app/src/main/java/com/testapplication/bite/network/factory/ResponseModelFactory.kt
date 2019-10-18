package com.testapplication.bite.network.factory

import com.testapplication.bite.network.model.Book
import com.testapplication.bite.network.model.Response
import org.json.JSONObject

class ResponseModelFactory : DTOModelFactory<Response<Book>> {

    override fun parse(json: JSONObject):Response<Book> {
        val status = json.optString("status")
        val copyright = json.optString("copyright")
        val numResults = json.optInt("num_results")
        val results = mutableListOf<Book>()

        var books = json.getJSONArray("results")
        var bookModelFactory = BookModelFactory()
        for (i in 0 until books.length()) {
            val book = books.optJSONObject(i)
            results.add(bookModelFactory.parse(book))
        }

        return Response(
            status = status,
            copyright = copyright,
            numResults = numResults,
            results = results
        )
    }

}