package com.adnanbal.alzuracasestudy.util.extension

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.adnanbal.alzuracasestudy.util.SimpleDividerItemDecoration


//ext function to add divider/separator between items in the recyclerview
fun RecyclerView.setDivider(
    @DrawableRes drawableRes: Int? = null,
    showLastDivider: Boolean = true
) {
    this.addItemDecoration(
        SimpleDividerItemDecoration(
            context = context,
            drawableRes = drawableRes,
            showLastDivider = showLastDivider
        )
    )
}