package com.example.drumncode.ui.photolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drumncode.common.FlickrUtils.SMALL_360
import com.example.drumncode.common.FlickrUtils.getFlickrImageLink
import com.example.drumncode.data.models.PhotoListEntity
import com.example.drumncode.databinding.PhotoItemBinding
import com.example.drumncode.ui.common.PhotoViewHolder

class PhotoListAdapter(var loadNextData: (currentId: String, previousId: String?, nextId: String?) -> Unit) :
    ListAdapter<PhotoListEntity, PhotoViewHolder>(MovieComparator) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.bind(item)) {

            Glide.with(holder.itemView.context)
                .load(
                    getFlickrImageLink(
                        item.id.toString(),
                        item.secret.toString(),
                        item.server.toString(),
                        item.farm ?: 0,
                        SMALL_360
                    )
                )
                .into(holder.binding.imageView2)
            holder.binding.imageView.setOnClickListener {
                if (position != RecyclerView.NO_POSITION) {
                    val previousId = if (position > 0) getItem(position - 1) else null
                    val nextId = if (position < itemCount - 1) getItem(position + 1) else null

                    loadNextData(item?.id.toString(), previousId?.id, nextId?.id)

                }

            }
        }


    }
}

object MovieComparator : DiffUtil.ItemCallback<PhotoListEntity>() {
    override fun areItemsTheSame(oldItem: PhotoListEntity, newItem: PhotoListEntity): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoListEntity, newItem: PhotoListEntity): Boolean {
        return oldItem == newItem
    }
}