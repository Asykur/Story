package com.asykurkhamid.story.utils

import com.asykurkhamid.story.model.StoryItemModel

interface CallItemView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: StoryItemModel?)
}