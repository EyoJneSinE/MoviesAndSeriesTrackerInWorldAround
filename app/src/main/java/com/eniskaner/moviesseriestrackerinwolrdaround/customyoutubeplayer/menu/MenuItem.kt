package com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.menu

import android.view.View
import androidx.annotation.DrawableRes

data class MenuItem @JvmOverloads constructor(
    val text: String,
    @DrawableRes val icon: Int? = null,
    val onClickListener: View.OnClickListener
)
