package com.example.demoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items = ArrayList<RecyclerData>()
    fun setListData(data:ArrayList<RecyclerData>){
        this.items=data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val row= view.row
        val row1=view.row1
        val img=view.imagethumb
        fun bind(data:RecyclerData){
        row.text=data.name
        row1.text=data.description
            val url = data.owner.avatar_url
            Glide.with(img)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(img)

        }
    }
}