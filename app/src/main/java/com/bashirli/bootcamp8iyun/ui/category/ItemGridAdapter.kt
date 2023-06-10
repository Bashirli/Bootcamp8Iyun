package com.bashirli.bootcamp8iyun.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.bootcamp8iyun.databinding.ItemChipCategoryBinding
import com.bashirli.bootcamp8iyun.databinding.ItemGridCategoriesBinding
import com.bashirli.bootcamp8iyun.modul.category.CategoryResponseItem
import com.squareup.picasso.Picasso

class ItemGridAdapter: RecyclerView.Adapter<ItemGridAdapter.ItemGridViewHolder>() {
    private val arrayList=ArrayList<CategoryResponseItem>()

    inner class ItemGridViewHolder(val binding: ItemGridCategoriesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: CategoryResponseItem){
            binding.categoryData=item
            Picasso.get().load(item.image).into(binding.imageCategory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemGridViewHolder {
        val layout= ItemGridCategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemGridViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemGridViewHolder, position: Int) {
        val item=arrayList.get(position)
        holder.bind(item)
    }

    fun updateList(list:List<CategoryResponseItem>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}