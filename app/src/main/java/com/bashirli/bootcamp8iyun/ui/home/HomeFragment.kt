package com.bashirli.bootcamp8iyun.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bashirli.bootcamp8iyun.databinding.FragmentHomeBinding
import com.bashirli.bootcamp8iyun.modul.category.CategoryResponse
import com.bashirli.bootcamp8iyun.modul.product.ProductResponse
import com.bashirli.bootcamp8iyun.service.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
   private var _binding:FragmentHomeBinding?=null
    private val binding get()=_binding!!

    private val service=ApiUtils.getApi()
    private val categoriesAdapter=CategoriesAdapter()
    private val productsAdapter=ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        setRV()

        service.getCategories().enqueue(object: Callback<CategoryResponse>{
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>,
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        categoriesAdapter.updateList(it)
                    }
                }else{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })

        service.getProducts().enqueue(object:Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>,
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        productsAdapter.updateList(it)
                    }
                }else{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun setRV(){
        with(binding){
            rvCategories.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvCategories.adapter=categoriesAdapter

            rvProducts.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            rvProducts.adapter=productsAdapter


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}