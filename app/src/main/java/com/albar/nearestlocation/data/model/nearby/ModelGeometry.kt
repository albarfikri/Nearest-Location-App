package com.albar.nearestlocation.data.model.nearby

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelGeometry: Serializable {
    @SerializedName("location")
    lateinit var modelLocation: ModelLocation
}