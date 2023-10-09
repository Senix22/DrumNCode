package com.example.drumncode.ui.photodetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drumncode.common.FlickrUtils
import com.example.drumncode.data.models.PhotoDetailsEntity
import com.example.drumncode.databinding.ViewPagerItemBinding

class ViewPagerAdapter(
    private val labelList: List<PhotoDetailsEntity>,
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding =
            ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val label = labelList[position]
        holder.bind(label)
    }

    override fun getItemCount(): Int {
        return labelList.size
    }

    inner class ViewPagerHolder(private var itemHolderBinding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(itemHolderBinding.root) {
        fun bind(label: PhotoDetailsEntity) {
            itemHolderBinding.title.text = label.title
            itemHolderBinding.description.text = label.description
            Glide.with(itemView.context)
                .load(
                    FlickrUtils.getFlickrImageLink(
                        label.id,
                        label.secret.toString(),
                        label.server.toString(),
                        label.farm ?: 0,
                        FlickrUtils.SMALL_360
                    )
                )
                .into(itemHolderBinding.image)
        }
    }
}