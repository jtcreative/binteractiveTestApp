package com.testapplication.bite.network.api


import android.util.Log
import com.testapplication.bite.network.factory.DTOModelFactory
import org.json.JSONObject
import java.net.URL

abstract class RestApi<ReturnType>(val baseUrl: String) {

    val apiKeyEndpoint = "?api-key=NgGdHl8Hkyc2QwXJt5d21ELUSdxkOg1m"

    abstract val endpoint : String

    abstract val modelFactory : DTOModelFactory<ReturnType>

     fun get(query:String) :  ReturnType? {
        val fullURL = baseUrl.plus(endpoint).plus(apiKeyEndpoint).plus("&").plus(query)
        val result = URL(fullURL).readText()
        val jsonObject = JSONObject(result)
        Log.d("Request", result)
        return modelFactory.parse(jsonObject)
    }
}
