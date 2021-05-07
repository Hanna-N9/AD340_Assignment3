package com.neergsw.ad340_assignment


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Camera(
        @SerializedName("id")
        val id : String ,

        @SerializedName("Description")
        val description : String,

        @SerializedName("ImageUrl")
        val imageURL : String,

        @SerializedName("Type")
        val type: String

)


