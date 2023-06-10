package com.bashirli.bootcamp8iyun.ui.selectedproducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.bootcamp8iyun.databinding.ItemSelectedProductsBinding
import com.bashirli.bootcamp8iyun.modul.product.ProductResponseItem
import com.squareup.picasso.Picasso

class SelectedCategoryAdapter : RecyclerView.Adapter<SelectedCategoryAdapter.SelectedViewHolder>() {

    private val arrayList=ArrayList<ProductResponseItem>()

    inner class SelectedViewHolder(val binding:ItemSelectedProductsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:ProductResponseItem){
            binding.productData=item
            Picasso.get().load(item.images.get(0)).into(binding.imageProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        val layout=ItemSelectedProductsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SelectedViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
       val item=arrayList.get(position)

        holder.bind(item)
    }

    fun updateList(list:List<ProductResponseItem>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}