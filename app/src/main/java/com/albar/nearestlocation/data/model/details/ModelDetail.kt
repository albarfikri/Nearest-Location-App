package com.albar.nearestlocation.data.model.details

import com.albar.nearestlocation.data.model.nearby.ModelGeometry
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelDetail : Serializable {
    @SerializedName("geometry")
    lateinit var modelGeometry: ModelGeometry

    @SerializedName("opening_hours")
    lateinit var modelOpening: ModelOpening

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("formatted_phone_number")
    var formatted_phone_number: String? = null


    @SerializedName("rating")
    var rating = 0.0
}