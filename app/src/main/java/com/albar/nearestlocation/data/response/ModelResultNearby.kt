package com.albar.nearestlocation.data.response

import com.albar.nearestlocation.data.model.nearby.ModelResults
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelResultNearby : Serializable {
    @SerializedName("results")
    lateinit var modelResults: List<ModelResults>
}