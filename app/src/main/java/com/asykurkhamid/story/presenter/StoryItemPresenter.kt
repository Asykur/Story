package com.asykurkhamid.story.presenter

import com.asykurkhamid.story.http.ServiceFactory
import com.asykurkhamid.story.model.StoryItemModel
import com.asykurkhamid.story.utils.CallItemView
import com.asykurkhamid.story.utils.CallListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryItemPresenter(private val view: CallItemView) {

    fun callItemStory(id: Int){
        val api = ServiceFactory.instanceServices()
        val callApi = api.getItem(id)

        callApi.enqueue(object : Callback<StoryItemModel>{
            override fun onFailure(call: Call<StoryItemModel>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(
                call: Call<StoryItemModel>,
                response: Response<StoryItemModel>
            ) {
                view.hideLoading()
                if (response.isSuccessful && response.body() != null){
                    view.showData(response.body())
                }
            }

        })
    }
}