package com.albar.nearestlocation.data.model.nearby

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelLocation : Serializable {
    @SerializedName("lat")
    var lat: Double = 0.0

    @SerializedName("lng")
    var lng: Double = 0.0
}