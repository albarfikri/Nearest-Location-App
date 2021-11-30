package com.albar.nearestlocation.data.response

import com.albar.nearestlocation.data.model.details.ModelDetail
import com.google.gson.annotations.SerializedName

class ModelResultDetail {
    @SerializedName("result")
    lateinit var modelDetail: ModelDetail
}