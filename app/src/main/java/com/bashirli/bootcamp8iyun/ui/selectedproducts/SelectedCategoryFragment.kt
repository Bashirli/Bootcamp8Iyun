package com.bashirli.bootcamp8iyun.ui.selectedproducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bashirli.bootcamp8iyun.R
import com.bashirli.bootcamp8iyun.databinding.FragmentHomeBinding
import com.bashirli.bootcamp8iyun.databinding.FragmentSelectedCategoryBinding
import com.bashirli.bootcamp8iyun.modul.category.CategoryResponse
import com.bashirli.bootcamp8iyun.modul.product.ProductResponse
import com.bashirli.bootcamp8iyun.service.ApiUtils
import com.bashirli.bootcamp8iyun.ui.home.CategoriesAdapter
import com.bashirli.bootcamp8iyun.ui.home.ProductsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedCategoryFragment : Fragment() {
    private var _binding: FragmentSelectedCategoryBinding?=null
    private val binding get()=_binding!!

    private val args by navArgs<SelectedCategoryFragmentArgs>()

    private lateinit var _categoryName:String
    private var _categoryId=0

    private val service= ApiUtils.getApi()
    private val adapter=SelectedCategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSelectedCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        _categoryId=args.categoryId
        _categoryName=args.categoryName

        with(binding){
            rvProducts.adapter=adapter
            textCategory.text=_categoryName
            progressBar.visibility=View.VISIBLE
        }


        val callback=service.getCategoryProducts(_categoryId)
        callback.enqueue(object:Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>,
            ) {
                binding.progressBar.visibility=View.GONE
                if(response.isSuccessful){
                    response.body()?.let {
                        adapter.updateList(it)
                    }
                }else{

                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                binding.progressBar.visibility=View.GONE
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}