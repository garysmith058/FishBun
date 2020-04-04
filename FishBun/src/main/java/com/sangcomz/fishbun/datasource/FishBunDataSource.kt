package com.sangcomz.fishbun.datasource

import android.net.Uri
import com.sangcomz.fishbun.MimeType
import com.sangcomz.fishbun.adapter.image.ImageAdapter
import com.sangcomz.fishbun.ui.album.model.AlbumMenuViewData
import com.sangcomz.fishbun.ui.album.model.AlbumViewData
import com.sangcomz.fishbun.ui.detail.model.DetailImageViewData

interface FishBunDataSource {
    fun getSelectedImageList(): List<Uri>
    fun selectImage(imageUri: Uri)
    fun unselectImage(imageUri: Uri)
    fun getPickerImages(): List<Uri>
    fun getMaxCount(): Int
    fun getMinCount(): Int
    fun getIsAutomaticClose(): Boolean
    fun getMessageLimitReached(): String
    fun getMessageNothingSelected(): String
    fun getExceptMimeTypeList(): List<MimeType>
    fun getSpecifyFolderList(): List<String>
    fun getAllViewTitle(): String
    fun getDetailViewData(): DetailImageViewData
    fun getAlbumViewData(): AlbumViewData
    fun getImageAdapter(): ImageAdapter
    fun gatAlbumMenuViewData(): AlbumMenuViewData
}