package com.testapplication.bite.network.factory

import com.testapplication.bite.network.model.Book
import org.json.JSONObject

class BookModelFactory : DTOModelFactory<Book> {

    override fun parse(json: JSONObject): Book {
        val title = json.optString("title")
        val description = json.optString("description")
        val contributor = json.optString("contributor")
        val author = json.optString("author")
        val price = json.optDouble("price")

        return Book(
            title = title,
            description = description,
            contributor = contributor,
            author = author,
            price = price
        )
    }
}