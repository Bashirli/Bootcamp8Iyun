package com.bashirli.bootcamp8iyun.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bashirli.bootcamp8iyun.databinding.ItemCategoriesBinding
import com.bashirli.bootcamp8iyun.modul.category.CategoryResponseItem
import com.squareup.picasso.Picasso

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val arrayList=ArrayList<CategoryResponseItem>()


    inner class CategoriesViewHolder(val binding:ItemCategoriesBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:CategoryResponseItem){
            binding.categoryData=item
            Picasso.get().load(item.image).into(binding.imageCategory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layout=ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item=arrayList.get(position)
        holder.bind(item)
    }

    fun updateList(list:List<CategoryResponseItem>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}