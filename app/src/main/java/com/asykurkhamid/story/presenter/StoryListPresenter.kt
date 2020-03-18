package com.asykurkhamid.story.presenter

import com.asykurkhamid.story.http.ServiceFactory
import com.asykurkhamid.story.utils.CallListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryListPresenter(private val view: CallListView) {
    fun callStoryList(){
        view.showLoading()
        val api = ServiceFactory.instanceServices()
        val callApi = api.getTopStories()
        callApi.enqueue(object :Callback<ArrayList<Int>> {
            override fun onFailure(call: Call<ArrayList<Int>>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(call: Call<ArrayList<Int>>, response: Response<ArrayList<Int>>) {
                view.hideLoading()
                if (response.isSuccessful && response.body() != null){
                    view.showStory(response.body())
                }
            }

        })
    }
}