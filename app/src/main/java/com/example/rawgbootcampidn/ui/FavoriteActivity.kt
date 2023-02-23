package com.example.rawgbootcampidn.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawgbootcampidn.adapter.FavoriteAdapter
import com.example.rawgbootcampidn.data.database.GameEntity
import com.example.rawgbootcampidn.databinding.ActivityFavoriteBinding
import com.example.rawgbootcampidn.viewmodels.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val favoriteGameAdapter by lazy { FavoriteAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Favorite Game"
            setDisplayHomeAsUpEnabled(true)
        }

        favoriteViewModel.favoriteGameList.observe(this) { result ->
            if (result.isEmpty()) {
                binding.apply {
                    rvFavoriteGame.isVisible = false
                    emptyTv.isVisible = true
                    emptyIcon.isVisible = true
                }
            } else {
                binding.rvFavoriteGame.apply {
                    adapter = favoriteGameAdapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(
                        this@FavoriteActivity
                    )
                }

                favoriteGameAdapter.apply {
                    setData(result)
                    setOnItemClickCallback(object:FavoriteAdapter.IOnFavoriteItemCallBack{
                        override fun onFavoriteItemClickCallback(data: GameEntity) {
                            val intent = Intent(this@FavoriteActivity, FavoriteDetailActivity::class.java)
                            intent.putExtra(FavoriteDetailActivity.EXTRA_FAVORITE_GAME,data)
                            startActivity(intent)
                        }
                    })
                }
            }
        }

    }

}