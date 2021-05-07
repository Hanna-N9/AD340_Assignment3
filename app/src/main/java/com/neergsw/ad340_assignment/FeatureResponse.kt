package com.neergsw.ad340_assignment

import com.google.gson.annotations.SerializedName

/*
        data class Features(
                @SerializedName("Features")
                val features: List<Feature>
        )

        data class Feature(
                @SerializedName("PointCoordinate")
                val pointCoordinate: List<Double>,

                @SerializedName("Cameras")
                val cameras: List<Camera>
        )
*/

data class FeatureResponse(
        @SerializedName("Features")
        val getCamera : List<Camera>
)
