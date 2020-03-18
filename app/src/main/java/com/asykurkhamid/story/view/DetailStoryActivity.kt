package com.asykurkhamid.story.view

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.asykurkhamid.story.R
import com.asykurkhamid.story.model.StoryItemModel
import kotlinx.android.synthetic.main.activity_detail_story.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import java.text.SimpleDateFormat
import java.util.*

class DetailStoryActivity : AppCompatActivity() {

    private var story: StoryItemModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        val actionBar = supportActionBar
        val bundle = intent.extras
        if (bundle != null){
            story = intent.getParcelableExtra<StoryItemModel>("data")
            tvDetTitle.text = story?.title
            by.text = story?.by
            date.text = getStringFromDate(story?.time)
            descWeb.loadUrl(story?.url)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val resultIntent = Intent()
                resultIntent.putExtra("title", story?.title)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getStringFromDate(epoch: Long?): String? {
        val instant: Instant? = getInstantFrom(Date(epoch!! * 1000))
        val localDateTime: LocalDateTime? = getLocalTime(instant, null)
        val date: Date? = getDateFrom(localDateTime, null)
        val simpleDateFormat =
            SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return simpleDateFormat.format(date)
    }

    fun getInstantFrom(date: Date): Instant? {
        return Instant.ofEpochMilli(date.time)
    }

    fun getLocalTime(
        instant: Instant?,
        zoneId: ZoneId?
    ): LocalDateTime? {
        return if (zoneId != null) LocalDateTime.ofInstant(
            instant,
            zoneId
        ) else LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
    }

    fun getDateFrom(
        localDateTime: LocalDateTime?,
        zoneId: ZoneId?
    ): Date? {
        var offset = ZoneOffset.UTC
        if (zoneId != null) {
            offset = zoneId.rules.getOffset(Instant.now())
        }
        return Date(localDateTime?.toEpochSecond(offset)!! * 1000)
    }


}
