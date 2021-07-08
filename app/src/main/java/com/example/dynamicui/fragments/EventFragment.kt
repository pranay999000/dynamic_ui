package com.example.dynamicui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dynamicui.adapter.EventAdapter
import com.example.dynamicui.databinding.FragmentEventBinding
import com.example.dynamicui.models.Event
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class EventFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentEventBinding = FragmentEventBinding.inflate(inflater, container, false)

        val eventList: ArrayList<Event> = ArrayList()
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

        binding.eventRecyclerView.layoutManager = gridLayoutManager
        binding.eventRecyclerView.setHasFixedSize(true)

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val eventArray = obj.getJSONArray("events")

            for (i in 0 until eventArray.length()) {
                eventList.add(Event(
                    eventArray.getJSONObject(i).getString("image"),
                    eventArray.getJSONObject(i).getString("heading")
                ))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val eventAdapter = EventAdapter(context, eventList)
        binding.eventRecyclerView.adapter = eventAdapter

        return binding.root
    }

    private fun loadJSONFromAsset(): String {
        val json: String?

        try {
            val inputStream: InputStream = requireContext().assets.open("events.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charSet: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charSet)
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }

        return json
    }
}