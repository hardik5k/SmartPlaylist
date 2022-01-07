package com.example.smartplaylist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getData(@Url id: String): Call<YTData>
    /*
    ,@Header("key") key:String
    ,@Header("part") part:String
    ,@Header("fields") fields : String):Call<YTData>
    */

    //fun getData(): Call<YTData>
    //fun getData2(): Call<Medium>
    //fun getData1(): Call<Item>
    //@GET("posts")
    //fun getData(): Call<List<TestingClassItem>>
}
//https://www.googleapis.com/youtube/v3/videos?id=nfs8NYg7yQM&key=AIzaSyClQRybPNCwvDhk2RjT5Q7zVizvlWaSRZk&part=snippet,statistics&fields=items(id,snippet,statistics)"