package com.testapplication.bite.network.model

data class Response<ResultType>(
    val status : String,
    val copyright : String,
    val numResults : Int,
    val results : List<ResultType>
)