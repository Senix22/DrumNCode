package com.example.drumncode.ui.common

import androidx.recyclerview.widget.RecyclerView
import com.example.drumncode.data.models.PhotoListEntity
import com.example.drumncode.databinding.PhotoItemBinding

class PhotoViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PhotoListEntity?) {
        binding.text.text = item?.title.orEmpty()
    }
}