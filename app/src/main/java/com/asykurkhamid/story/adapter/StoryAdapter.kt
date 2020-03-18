package com.asykurkhamid.story.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asykurkhamid.story.R
import com.asykurkhamid.story.model.StoryItemModel
import com.asykurkhamid.story.view.DetailStoryActivity
import com.asykurkhamid.story.view.MainActivity
import kotlinx.android.synthetic.main.story_item.view.*

class StoryAdapter (private var list: ArrayList<StoryItemModel>):RecyclerView.Adapter<StoryAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.story_item,parent,false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(list[position])
    }

    fun setData(listStory: ArrayList<StoryItemModel>) {
        list = listStory
    }


    class VH(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        private val title = itemView.tvItemTitle
        private val score = itemView.tvItemScore
        private val comment = itemView.tvItemComment
        private val itemArea = itemView.areaItem
        private val ctx = title.context
        private lateinit var story : StoryItemModel

        init {
            itemArea.setOnClickListener(this)
        }

        fun bindData(data : StoryItemModel){
            story = data
            title.text = data.title
            score.text = "Score : "+data.score
            comment.text = "Total Comment : "+data.kids.size.toString()
        }

        override fun onClick(v: View?) {
            val intent = Intent(ctx, DetailStoryActivity::class.java)
            intent.putExtra("data",story)
            (ctx as MainActivity).startActivityForResult(intent,MainActivity.TITLE_REQUEST)
        }
    }
}