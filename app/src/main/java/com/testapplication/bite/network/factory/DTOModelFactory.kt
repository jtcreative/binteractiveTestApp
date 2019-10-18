package com.testapplication.bite.network.factory

import org.json.JSONObject

interface DTOModelFactory<T> {
    fun parse(json:JSONObject) : T
}