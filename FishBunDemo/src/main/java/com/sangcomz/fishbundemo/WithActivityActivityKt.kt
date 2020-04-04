package com.sangcomz.fishbundemo

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.FishBun.Companion.FISHBUN_REQUEST_CODE
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.sangcomz.fishbun.adapter.image.impl.PicassoAdapter
import kotlinx.android.synthetic.main.activity_withactivity.*
import java.util.*

class WithActivityActivityKt : AppCompatActivity() {

    var path = ArrayList<Uri>()
    private lateinit var imageAdapter: ImageAdapter
    private var mode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withactivity)

        mode = intent.getIntExtra("mode", -1)

        with(recyclerview) {
            layoutManager = LinearLayoutManager(
                this@WithActivityActivityKt,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            imageAdapter = ImageAdapter(this@WithActivityActivityKt, ImageController(img_main), path)
            adapter = imageAdapter
        }
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        imageData: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, imageData)

        if (requestCode == FishBun.FISHBUN_REQUEST_CODE && resultCode == RESULT_OK) {
            path = imageData!!.getParcelableArrayListExtra(FishBun.INTENT_PATH)
            imageAdapter.changePath(path)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_plus) {
            when (mode) {
                //basic
                0 -> {
                    FishBun.with(this@WithActivityActivityKt)
                        .setImageAdapter(GlideAdapter())
                        .setIsUseAllDoneButton(true)
                        .setMenuDoneText("Choose")
                        .setMenuAllDoneText("Choose All")
                        .startAlbum()
                }
                //dark
                1 -> {
                    FishBun.with(this@WithActivityActivityKt)
                        .setImageAdapter(PicassoAdapter())
                        .setMaxCount(5)
                        .setMinCount(3)
                        .setPickerSpanCount(5)
                        .setActionBarColor(
                            Color.parseColor("#795548"),
                            Color.parseColor("#5D4037"),
                            false
                        )
                        .setActionBarTitleColor(Color.parseColor("#ffffff"))
                        .setSelectedImages(path)
                        .setAlbumSpanCount(2, 3)
                        .setButtonInAlbumActivity(false)
                        .setCamera(true)
                        .exceptGif(true)
                        .setReachLimitAutomaticClose(true)
                        .setHomeAsUpIndicatorDrawable(
                            ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_custom_back_white
                            )
                        )
                        .setDoneButtonDrawable(
                            ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_custom_ok
                            )
                        )
                        .setAllViewTitle("All")
                        .setActionBarTitle("FishBun Dark")
                        .textOnNothingSelected("Please select three or more!")
                        .startAlbum()
                }
                //Light
                2 -> {
                    FishBun.with(this@WithActivityActivityKt)
                        .setImageAdapter(PicassoAdapter())
                        .setMaxCount(50)
                        .setPickerSpanCount(4)
                        .setActionBarColor(
                            Color.parseColor("#ffffff"),
                            Color.parseColor("#ffffff"),
                            true
                        )
                        .setActionBarTitleColor(Color.parseColor("#000000"))
                        .setSelectedImages(path)
                        .setAlbumSpanCount(1, 2)
                        .setButtonInAlbumActivity(true)
                        .setCamera(false)
                        .exceptGif(true)
                        .setReachLimitAutomaticClose(false)
                        .setHomeAsUpIndicatorDrawable(
                            ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_arrow_back_black_24dp
                            )
                        )

                        .setIsUseAllDoneButton(true)
                        .setMenuDoneText("Choose")
                        .setMenuAllDoneText("Choose All")
                        .setIsUseAllDoneButton(true)
                        .setAllViewTitle("All of your photos")
                        .setActionBarTitle("FishBun Light")
                        .textOnImagesSelectionLimitReached("You can't select any more.")
                        .textOnNothingSelected("I need a photo!")
                        .startAlbum()
                }
                else -> {
                    finish()
                }
            }

            return true
        }
        return super.onOptionsItemSelected(item)
    }
}