package com.example.githubapisample.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubapisample.databinding.ListItemRepoBinding
import com.example.githubapisample.domain.vo.Repo
import com.example.githubapisample.ui.common.DataBindingAdapter

class RepoAdapter : DataBindingAdapter<Repo, ListItemRepoBinding>() {

    override fun createBinding(parent: ViewGroup): ListItemRepoBinding =
        ListItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: ListItemRepoBinding, item: Repo) {
        binding.item = item
    }
}
