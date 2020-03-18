package com.asykurkhamid.story.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class StoryItemModel(
    val by : String,
    val descendants : Int,
    val kids : ArrayList<String>,
    val score : String,
    val time : Long,
    val title : String,
    val type: String,
    val url: String
):Parcelable