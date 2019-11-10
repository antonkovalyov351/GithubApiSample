package com.example.githubapisample.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun View.bindIsGone(isGone: Boolean) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}
