package com.example.postrequestapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequestapp.databinding.ItemRowBinding


class rvAdapter (var listalluser: Users) : RecyclerView.Adapter<rvAdapter.ViewHolder>(){
    class ViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listalluser[position]

        holder.binding.apply {
            tv2.text = user.name
            tv3.text = user.location
            tv4.text = user.pk.toString()
        }
    }

    override fun getItemCount(): Int = listalluser.size


    //...
    fun update(listalluser: Users){
        this.listalluser = listalluser
        notifyDataSetChanged()
    }

}