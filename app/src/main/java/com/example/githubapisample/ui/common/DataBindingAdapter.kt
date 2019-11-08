package com.example.githubapisample.ui.common

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingAdapter<T, V : ViewDataBinding>
    : RecyclerView.Adapter<DataBindingViewHolder<V>>() {

    private val data = arrayListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<V> {
        val binding = createBinding(parent)
        return DataBindingViewHolder(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    override fun onBindViewHolder(holder: DataBindingViewHolder<V>, position: Int) {
        bind(holder.binding, data[position])
        holder.binding.executePendingBindings()
    }

    protected abstract fun bind(binding: V, item: T)

    override fun getItemCount() = data.size

    fun swapData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}
