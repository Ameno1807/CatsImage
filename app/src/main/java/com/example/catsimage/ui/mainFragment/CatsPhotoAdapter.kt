package com.example.catsimage.ui.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catsimage.R
import com.example.catsimage.data.remote.retrofit.responce.CatsPhoto
import com.example.catsimage.databinding.ItemPhotoCatBinding

class CatsPhotoAdapter(private val listener: onItemClickListener) :
    PagingDataAdapter<CatsPhoto, CatsPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemPhotoCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoCatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: CatsPhoto) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(photo.url)
                    .centerCrop()
                    .error(R.drawable.ic_baseline_error_24)
                    .into(imageView)

                name.text = photo.name
            }
        }

    }

    interface onItemClickListener {
        fun onItemClick(photo: CatsPhoto)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<CatsPhoto>() {
            override fun areItemsTheSame(
                oldItem: CatsPhoto,
                newItem: CatsPhoto
            ) =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: CatsPhoto,
                newItem: CatsPhoto
            ) =
                oldItem == newItem
        }
    }
}
