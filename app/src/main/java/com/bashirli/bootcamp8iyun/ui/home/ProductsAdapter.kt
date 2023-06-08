package com.bashirli.bootcamp8iyun.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.bootcamp8iyun.databinding.ItemProductsBinding
import com.bashirli.bootcamp8iyun.modul.product.ProductResponseItem
import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val arrayList=ArrayList<ProductResponseItem>()

    inner class ProductsViewHolder(val binding:ItemProductsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:ProductResponseItem){
            binding.productItem=item
            Picasso.get().load(item.images[0]).into(binding.imageProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layout=ItemProductsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductsViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
          val item=arrayList.get(position)
           holder.bind(item)
    }

    fun updateList(list:List<ProductResponseItem>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}