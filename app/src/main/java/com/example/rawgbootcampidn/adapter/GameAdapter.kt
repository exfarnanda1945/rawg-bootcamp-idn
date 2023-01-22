package com.example.rawgbootcampidn.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rawgbootcampidn.databinding.GameRowLayoutBinding
import com.example.rawgbootcampidn.model.GameResult

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<GameResult>() {
        override fun areItemsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    private lateinit var onItemCallBack: IOnItemCallBack

    inner class GameViewHolder(private val binding: GameRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GameResult) {
            binding.apply {
                data = item
                itemView.setOnClickListener {
                    onItemCallBack.onItemClickCallback(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            GameRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val itemData = differ.currentList[position]
        holder.bind(itemData)
    }

    fun setData(list: List<GameResult?>?) {
        differ.submitList(list)
    }

    fun setOnItemClickCallback(action: IOnItemCallBack) {
        this.onItemCallBack = action
    }

    interface IOnItemCallBack {
        fun onItemClickCallback(data: GameResult)
    }
}