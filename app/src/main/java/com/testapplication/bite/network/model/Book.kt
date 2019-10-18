package com.testapplication.bite.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title : String,
    val description : String,
    val contributor : String,
    val author : String,
    val price : Double
) : Parcelable