package com.neergsw.ad340_assignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CameraApiClient {

    companion object{
        const val BASE_URL = "https://web6.seattle.gov/"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}

