package com.example.rawgbootcampidn.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rawgbootcampidn.databinding.ScreenshotLayoutBinding
import com.example.rawgbootcampidn.model.ScreenshotsResult

class ScreenshotGameAdapter : RecyclerView.Adapter<ScreenshotGameAdapter.ScreenshotsViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<ScreenshotsResult>() {
        override fun areItemsTheSame(
            oldItem: ScreenshotsResult,
            newItem: ScreenshotsResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ScreenshotsResult,
            newItem: ScreenshotsResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    inner class ScreenshotsViewHolder(private val binding: ScreenshotLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(screenshot: ScreenshotsResult) {
            binding.screenshot = screenshot
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotsViewHolder {
        return ScreenshotsViewHolder(
            ScreenshotLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    fun setData(list: List<ScreenshotsResult?>?) {
        differ.submitList(list)
    }
}