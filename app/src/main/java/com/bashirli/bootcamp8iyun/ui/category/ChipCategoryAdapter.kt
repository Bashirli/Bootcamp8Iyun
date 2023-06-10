package com.bashirli.bootcamp8iyun.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.bootcamp8iyun.databinding.ItemChipCategoryBinding
import com.bashirli.bootcamp8iyun.modul.category.CategoryResponseItem

class ChipCategoryAdapter : RecyclerView.Adapter<ChipCategoryAdapter.ChipViewHolder>() {
    private val arrayList=ArrayList<CategoryResponseItem>()
    inner class ChipViewHolder(val binding:ItemChipCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:CategoryResponseItem){
            binding.categoryData=item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val layout=ItemChipCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChipViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val item=arrayList.get(position)
        holder.bind(item)
        holder.binding.chipButton.setOnClickListener {
            it.findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToSelectedCategoryFragment2(item.name,item.id))
        }
    }

    fun updateList(list:List<CategoryResponseItem>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}