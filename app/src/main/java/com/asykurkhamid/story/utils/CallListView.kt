package com.asykurkhamid.story.utils

interface CallListView {
    fun showLoading()
    fun hideLoading()
    fun showStory(datas: ArrayList<Int>?)
}