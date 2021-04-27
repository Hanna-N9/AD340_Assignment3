package com.neergsw.recltest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neergsw.ad340_assignment.R


class MovieAdapter(
        private val context: Context,
        private val list: List<MovieArray>,
        val listener: (MovieArray) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


   //Need to build view holder class before starting the previous methods. Everytime you have a RecyclerView, you need to build a custom
   //view holder class that describes what the views will look like in RecyclerView so you want to take the data set and set it to a view holder
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val imageSrc = view.findViewById<ImageView>(R.id._image)
        val theTitle = view.findViewById<TextView>(R.id.movie_title)
        val theYear = view.findViewById<TextView>(R.id.movie_year)

        fun bindView(data: MovieArray, listener: (MovieArray) -> Unit) {
            imageSrc.setImageResource(data.imageSrc)
            theTitle.text = data.title
            theYear.text = data.year
            itemView.setOnClickListener { listener(data) }
        }
    }

 //Implemented -- Required methods for RecyclerView Adapter to make it work

    //Tells RecyclerView to create the different view holders in the list -- There are 21 movie data set so each card are view holders
    //RecyclerView keeps items visible in memory -- as a user scrolls, one sees the view holder visible kept in memory, while the previous
    //viewholders and couple below not in view are taken out. So this is advantage of saving memory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    //Tells RecyclerView how many items are inside the list
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(list[position], listener)
    }
}
