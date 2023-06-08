package com.bashirli.bootcamp8iyun.service

import com.bashirli.bootcamp8iyun.modul.category.CategoryResponse
import com.bashirli.bootcamp8iyun.modul.product.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("products")
    fun getProducts():Call<ProductResponse>

    @GET("categories")
    fun getCategories():Call<CategoryResponse>

    // CategoryResponse = List<CategoryResponseItem>

}