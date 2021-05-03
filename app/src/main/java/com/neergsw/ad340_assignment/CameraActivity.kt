package com.neergsw.ad340_assignment


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        call.getCameraList().enqueue(object : Callback<FeatureResponse> {

            override fun onFailure(call: Call<FeatureResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<FeatureResponse>, response: Response<FeatureResponse>) {
                return callback(response.body()!!.getCamera)
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



