package com.example.hellogarden.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hellogarden.GardenRequestFragmentDirections
import com.example.hellogarden.R
import com.example.hellogarden.data.models.ProductArticle

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    private var dataset = listOf<ProductArticle>()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val card: CardView = view.findViewById(R.id.product_card)
    }

    fun submitList(list: List<ProductArticle>) {
        dataset = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.garden_item, parent, false)

        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: ProductArticle = dataset[position]



        holder.card.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(GardenRequestFragmentDirections.actionGardenRequestFragmentToDetailProductFragment(item.id))
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
