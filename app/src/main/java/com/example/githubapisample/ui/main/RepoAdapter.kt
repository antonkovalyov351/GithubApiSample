package com.example.githubapisample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapisample.R
import com.example.githubapisample.domain.vo.Repo
import kotlinx.android.synthetic.main.list_item_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    private val data = arrayListOf<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateData(data: List<Repo>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}

class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Repo) {
        itemView.repo_name.text = item.name
    }
}
