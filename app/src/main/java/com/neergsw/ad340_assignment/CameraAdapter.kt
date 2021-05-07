package com.neergsw.ad340_assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CameraAdapter(
    private val dataList: List<Camera>
) : RecyclerView.Adapter<CameraAdapter.CameraViewHolder>() {

    class CameraViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val IMAGE_BASE = "https://www.seattle.gov/trafficcams/images/"

        val theDesc = itemView.findViewById<TextView>(R.id.camera_desc)
        val imgSrc = itemView.findViewById<ImageView>(R.id.camera_img)

        fun bindView(data: Camera) {
            theDesc.text = data.description
/*
Glide or Picasso (both work)
            Picasso.get().load(IMAGE_BASE + data.imageURL).into(imgSrc)
*/
            Glide.with(itemView).load(IMAGE_BASE + data.imageURL).into(imgSrc)

        }
    }

//Implemented

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        return CameraViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.camera_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        holder.bindView(dataList.get(position))
    }
}


