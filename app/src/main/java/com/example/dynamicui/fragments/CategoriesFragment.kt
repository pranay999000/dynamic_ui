package com.example.dynamicui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dynamicui.adapter.CategoriesAdapter
import com.example.dynamicui.databinding.FragmentCategoriesBinding
import com.example.dynamicui.models.Event
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class CategoriesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentCategoriesBinding = FragmentCategoriesBinding.inflate(inflater, container, false)

        val categoriesList: ArrayList<Event> = ArrayList()
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.categoriesRecyclerView.layoutManager = linearLayoutManager
        binding.categoriesRecyclerView.setHasFixedSize(true)

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val categoriesArray = obj.getJSONArray("categories")

            for (i in 0 until categoriesArray.length()) {
                categoriesList.add(Event(
                        categoriesArray.getJSONObject(i).getString("image"),
                        categoriesArray.getJSONObject(i).getString("heading")
                ))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val categoriesAdapter = CategoriesAdapter(context, categoriesList)
        binding.categoriesRecyclerView.adapter = categoriesAdapter

        return binding.root
    }

    private fun loadJSONFromAsset(): String {
        val json: String?

        try {
            val inputStream: InputStream = requireContext().assets.open("categories.json")
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