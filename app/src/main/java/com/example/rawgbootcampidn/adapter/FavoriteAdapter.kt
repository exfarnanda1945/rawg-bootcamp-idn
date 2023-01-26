package com.example.rawgbootcampidn.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rawgbootcampidn.data.database.GameEntity
import com.example.rawgbootcampidn.databinding.FavoriteGameRowLayoutBinding

class FavoriteAdapter:RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<GameEntity>() {
        override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    private lateinit var onFavoriteItemCallBack: IOnFavoriteItemCallBack

    inner class FavoriteViewHolder(private val binding:FavoriteGameRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GameEntity) {
            binding.apply {
                gameDetail = item.game
                itemView.setOnClickListener {
                    onFavoriteItemCallBack.onFavoriteItemClickCallback(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoriteGameRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val itemData = differ.currentList[position]
        holder.bind(itemData)
    }

    fun setData(list: List<GameEntity?>?) {
        differ.submitList(list)
    }

    fun setOnItemClickCallback(action: IOnFavoriteItemCallBack) {
        this.onFavoriteItemCallBack = action
    }

    interface IOnFavoriteItemCallBack {
        fun onFavoriteItemClickCallback(data: GameEntity)
    }
}