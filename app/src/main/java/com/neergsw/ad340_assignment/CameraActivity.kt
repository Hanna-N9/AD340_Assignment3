package com.neergsw.ad340_assignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        getCameraData { cameras: List<Camera> ->
            recyclerView.adapter = CameraAdapter(cameras)
        }

    }

    private fun getCameraData(callback: (List<Camera>) -> Unit){

        val call = CameraApiClient.getInstance().create(CameraApiInterface::class.java)
        call.getCameraList().enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                try {
                    val features = response.body()?.get("Features")!!.asJsonArray
                    val cameras : MutableList<Camera> = ArrayList()
                    for (i in 0 until features.size()) {
                        val location = features[i].asJsonObject
                        // points may have more than one camera
                        val camera = location.getAsJsonArray("Cameras")[0].asJsonObject
                        cameras.add(
                            Camera(
                                camera.get("Id").asString,
                                camera.get("Description").asString,
                                camera.get("ImageUrl").asString,
                                camera.get("Type").asString
                            )
                        )
                    }
                    return callback(cameras)
                } catch (e: Exception) {
                    Log.e("TAG", "exception" + e.localizedMessage)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

//backButton

        val backButton = supportActionBar

//!! is  non-null type
        backButton!!.title = "Camera Lists"

//Enable back button so we need to call on setDisplayHomeAsUpEnabled
        backButton.setDisplayHomeAsUpEnabled(true)
    }

}


