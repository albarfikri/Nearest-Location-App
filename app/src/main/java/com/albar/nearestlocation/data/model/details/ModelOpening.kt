package com.albar.nearestlocation.data.model.details

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelOpening: Serializable {
    @SerializedName("open_now")
    var openNow: Boolean? = null

    @SerializedName("weekday_text")
    lateinit var weekdayText: List<String>
}