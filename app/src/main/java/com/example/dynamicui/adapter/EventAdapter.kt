package com.example.dynamicui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicui.databinding.ItemEventsBinding
import com.example.dynamicui.models.Event
import com.squareup.picasso.Picasso

class EventAdapter(private var context: Context?, private var list: ArrayList<Event>):
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

            inner class ViewHolder(val binding: ItemEventsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder) {
            with (list[position]) {
                Picasso.get().load(image).into(binding.eventImage)
                binding.eventHeading.text = heading
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}