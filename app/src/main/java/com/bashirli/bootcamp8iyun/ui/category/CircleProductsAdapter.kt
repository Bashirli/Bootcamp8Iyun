package com.bashirli.bootcamp8iyun.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.bootcamp8iyun.databinding.ItemChipCategoryBinding
import com.bashirli.bootcamp8iyun.databinding.ItemCircleProductsBinding
import com.bashirli.bootcamp8iyun.modul.category.CategoryResponseItem
import com.bashirli.bootcamp8iyun.modul.product.ProductResponseItem
import com.squareup.picasso.Picasso

class CircleProductsAdapter : RecyclerView.Adapter<CircleProductsAdapter.CircleViewHolder>() {
    private val arrayList=ArrayList<ProductResponseItem>()
    inner class CircleViewHolder(val binding: ItemCircleProductsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ProductResponseItem){
            Picasso.get().load(item.images.get(0)).into(binding.imageProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val layout= ItemCircleProductsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CircleViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        val item=arrayList.get(position)
        holder.bind(item)
    }

    fun updateList(list:List<ProductResponseItem>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}