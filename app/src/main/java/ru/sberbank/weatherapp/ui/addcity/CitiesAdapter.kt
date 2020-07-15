package ru.sberbank.weatherapp.ui.addcity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.add_city_item.view.*
import ru.sberbank.weatherapp.R
import ru.sberbank.weatherapp.domain.model.City

class CitiesAdapter(val onItemClick: (City) -> Unit) :
    RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {
    private val cities = mutableListOf<City>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.add_city_item, parent, false)
        return CitiesViewHolder(v)
    }

    fun setList(categories: List<City>) {
        cities.clear()
        cities.addAll(categories)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(cities[position],onItemClick)
    }

    inner class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: City,onItemClick: (City) -> Unit) {
            itemView.city_name.text = city.displayName
            itemView.setOnClickListener {
                onItemClick(city)
            }
        }

    }
}