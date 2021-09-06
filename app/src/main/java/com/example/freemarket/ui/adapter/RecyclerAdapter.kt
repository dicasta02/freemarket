package com.example.freemarket.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.freemarket.R
import com.example.freemarket.data.models.Products
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapter(var context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), Filterable {

    var data: ArrayList<Products>
    var dataSearch: ArrayList<Products>
    var itemClickListener: OnItemClickListener? = null

    init {
        this.data = ArrayList()
        this.dataSearch = ArrayList()
    }

    fun setDataValue(data: ArrayList<Products>?) {
        dataSearch.clear()
        this.data.clear()
        this.data.addAll(data!!)
        this.dataSearch.addAll(data)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charText = charSequence.toString().lowercase(Locale.getDefault())
                data.clear()
                if (charText.isEmpty()) {
                    data.addAll(dataSearch)
                } else {
                    for (video in dataSearch) {
                        if (charText.isNotEmpty() && video.title.lowercase(Locale.getDefault())
                                .contains(charText)
                        ) {
                            data.add(video)
                        }
                    }
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = data
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: Filter.FilterResults
            ) {
                @Suppress("UNCHECKED_CAST")
                data = filterResults.values as ArrayList<Products>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recicler_item, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.itemView.txtTitle.text = data[position].title

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {

                itemClickListener?.onItemClicked(data.get(adapterPosition))
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClicked(products: Products)
    }

}