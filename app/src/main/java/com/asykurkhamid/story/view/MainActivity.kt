package com.asykurkhamid.story.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.asykurkhamid.story.R
import com.asykurkhamid.story.adapter.StoryAdapter
import com.asykurkhamid.story.model.StoryItemModel
import com.asykurkhamid.story.presenter.StoryItemPresenter
import com.asykurkhamid.story.presenter.StoryListPresenter
import com.asykurkhamid.story.utils.CallItemView
import com.asykurkhamid.story.utils.CallListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),CallListView,CallItemView {

    private lateinit var adapter: StoryAdapter
    private lateinit var listPresenter: StoryListPresenter
    private lateinit var itemPresenter: StoryItemPresenter
    private var listID: ArrayList<Int> = ArrayList()
    private var listStory: ArrayList<StoryItemModel> = ArrayList()
    companion object{
        const val TITLE_REQUEST = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPresenter = StoryListPresenter(this)
        listPresenter.callStoryList()

        itemPresenter = StoryItemPresenter(this)


        adapter = StoryAdapter(listStory)
        rvStoryList.layoutManager = GridLayoutManager(this,2)
        rvStoryList.adapter = adapter

    }

    override fun showLoading() {
        pgMain.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pgMain.visibility = View.GONE
    }

    override fun showData(data: StoryItemModel?) {
        if (data != null) {
            listStory.add(data)
            if (listStory.size == 10){
                adapter.setData(listStory)
                adapter.notifyDataSetChanged()
            }
        }
    }


    override fun showStory(datas: ArrayList<Int>?) {
        if (datas != null && datas.size != 0){
            var limit = 10
            for (i in 0..datas.size-1){
                listID.add( datas[i])
                if (listID.size == limit){
                    for (i in 0..listID.size-1){
                        callPerItem(listID[i])
                    }
                }
            }
        }
    }

    fun callPerItem(id : Int){
        lifecycleScope.launch {
            itemPresenter.callItemStory(id)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TITLE_REQUEST){
            if (resultCode == Activity.RESULT_OK){
                tvMainTitle.text = data?.getStringExtra("title")
            }
        }

    }
}
