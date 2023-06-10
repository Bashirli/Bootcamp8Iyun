package com.bashirli.bootcamp8iyun.service

import com.bashirli.bootcamp8iyun.modul.category.CategoryResponse
import com.bashirli.bootcamp8iyun.modul.product.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("products")
    fun getProductsWithLimit(@Query("limit") limit:Int,
                    @Query("offset") offset:Int,
                    ):Call<ProductResponse>


    @GET("products")
    fun getProducts():Call<ProductResponse>

    @GET("products")
    fun getCategoryProducts(@Query("categoryId") id:Int):Call<ProductResponse>

    @GET("categories")
    fun getCategories():Call<CategoryResponse>

    // CategoryResponse = List<CategoryResponseItem>

}