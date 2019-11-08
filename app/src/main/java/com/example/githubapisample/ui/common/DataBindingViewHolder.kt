package com.example.githubapisample.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<out T : ViewDataBinding> constructor(
    val binding: T
) : RecyclerView.ViewHolder(binding.root)
