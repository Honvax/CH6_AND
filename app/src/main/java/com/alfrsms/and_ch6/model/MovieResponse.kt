package com.alfrsms.and_ch6.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val movies : List<Data>
) : Parcelable {
    constructor() : this(mutableListOf())
}