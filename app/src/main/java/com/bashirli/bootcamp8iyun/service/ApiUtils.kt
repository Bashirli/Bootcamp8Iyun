package com.bashirli.bootcamp8iyun.service

import com.bashirli.bootcamp8iyun.util.Constants.BASE_URL

class ApiUtils {
    companion object{
        fun getApi():Service{
            return RetrofitUtils.getRetrofit(BASE_URL).create(Service::class.java)
        }
    }
}