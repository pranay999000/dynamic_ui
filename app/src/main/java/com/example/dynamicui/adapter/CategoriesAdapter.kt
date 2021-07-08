package com.example.dynamicui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicui.databinding.CategoriesItemBinding
import com.example.dynamicui.models.Event
import com.squareup.picasso.Picasso

class CategoriesAdapter(private val context: Context?, private val list: ArrayList<Event>):
        RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CategoriesItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder) {
            with (list[position]) {
                Picasso.get().load(image).into(binding.categoriesImage)
                binding.categoriesHeading.text = heading
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}