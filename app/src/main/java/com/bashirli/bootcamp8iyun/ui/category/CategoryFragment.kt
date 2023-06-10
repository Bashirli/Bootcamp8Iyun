package com.bashirli.bootcamp8iyun.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bashirli.bootcamp8iyun.R
import com.bashirli.bootcamp8iyun.databinding.FragmentCategoryBinding
import com.bashirli.bootcamp8iyun.modul.category.CategoryResponse
import com.bashirli.bootcamp8iyun.modul.product.ProductResponse
import com.bashirli.bootcamp8iyun.service.ApiUtils
import com.bashirli.bootcamp8iyun.service.RetrofitUtils
import com.bashirli.bootcamp8iyun.ui.home.CategoriesAdapter
import com.bashirli.bootcamp8iyun.ui.home.ProductsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment() {
   private var _binding:FragmentCategoryBinding?=null
   private val binding get()=_binding!!

   private val itemCircleProductsAdapter=CircleProductsAdapter()
   private val itemGridAdapter=ItemGridAdapter()
   private val chipCategoryAdapter=ChipCategoryAdapter()
   private val productsAdapter=ProductsAdapter()

   private val service=ApiUtils.getApi()

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding= FragmentCategoryBinding.inflate(inflater,container,false)
      return binding.root
   }


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      setup()
   }

   private fun setup(){

      with(binding){
         rvChipCategories.adapter=chipCategoryAdapter
         rvCircleProducts.adapter=itemCircleProductsAdapter
         rvGridCategories.adapter=itemGridAdapter
         rvProducts.adapter=productsAdapter
      }

      loadData()
   }

   private fun loadData(){
      val productCallback=service.getProducts()
      val categoriesCallback=service.getCategories()

      productCallback.enqueue(object: Callback<ProductResponse>{
         override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
            if(response.isSuccessful){
               response.body()?.let {
                  productsAdapter.updateList(it)
                  itemCircleProductsAdapter.updateList(it)
               }
            }else{
               Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
            }
         }

         override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
            Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
         }

      })

      categoriesCallback.enqueue(object:Callback<CategoryResponse>{
         override fun onResponse(
            call: Call<CategoryResponse>,
            response: Response<CategoryResponse>,
         ) {
          if(response.isSuccessful){
             response.body()?.let {
                chipCategoryAdapter.updateList(it)
                itemGridAdapter.updateList(it)
             }
          }else{
             Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
          }
         }

         override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
            Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
         }

      })


   }



   override fun onDestroy() {
      super.onDestroy()
      _binding=null
   }


}