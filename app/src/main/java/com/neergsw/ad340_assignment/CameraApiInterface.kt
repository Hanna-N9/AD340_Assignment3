package com.neergsw.ad340_assignment

import retrofit2.Call
import retrofit2.http.GET

interface CameraApiInterface {

    @GET("Travelers/api/Map/Data?zoomId=13&type=2")
    fun getCameraList(): Call<FeatureResponse>
}
