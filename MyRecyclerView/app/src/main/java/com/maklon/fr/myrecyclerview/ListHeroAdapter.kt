package com.maklon.fr.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListHeroAdapter(private val listHero:ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    //membuat fungsi onclickItem RecyclerView
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHero[position]

        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .circleCrop() // Mengubah image menjadi lingkaran
            .into(holder.imgPhoto) // imag

        //holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        //set OnclickListener RecyclerView
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
        /*  holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Kamu melilih " +listHero[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()
        }*/
    }


    override fun getItemCount():  Int = listHero.size

          class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
            var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
            var tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

}