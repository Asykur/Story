package com.asykurkhamid.story.http

import com.asykurkhamid.story.model.StoryItemModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {
    @GET("topstories.json?print=pretty")
    fun getTopStories():Call<ArrayList<Int>>

    @GET("item/{id}.json?print=pretty")
    fun getItem(
        @Path("id") id: Int
    ):Call<StoryItemModel>

}