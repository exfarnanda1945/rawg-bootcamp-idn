package com.example.rawgbootcampidn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawgbootcampidn.adapter.ScreenshotGameAdapter
import com.example.rawgbootcampidn.data.database.GameEntity
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.databinding.ActivityFavoriteDetailBinding
import com.example.rawgbootcampidn.viewmodels.FavoriteDetailViewModel

class FavoriteDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteDetailBinding
    private val favoriteDetailViewModel by viewModels<FavoriteDetailViewModel>()
    private val screenshotAdapter by lazy { ScreenshotGameAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Favorite Game Detail"
            setDisplayHomeAsUpEnabled(true)
        }

        val favoriteGame = intent.getParcelableExtra<GameEntity>(EXTRA_FAVORITE_GAME)
        binding.gameDetail = favoriteGame!!.game

        favoriteDetailViewModel.fetchScreenshotsGame(favoriteGame.id)
        favoriteDetailViewModel.screenshotsGame.observe(this) { result ->
            if (result is NetworkResult.Success) {
                binding.screenshotsGame.apply {
                    adapter = screenshotAdapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(
                        this@FavoriteDetailActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    screenshotAdapter.setData(result.data?.results)
                }
            }
        }

        binding.removeFavoriteBtn.setOnClickListener {
            deleteFavoriteGame(favoriteGame)
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun deleteFavoriteGame(gameEntity: GameEntity) {
        favoriteDetailViewModel.deleteFavoriteGame(gameEntity)
        Toast.makeText(this, "Successfully remove from favorite", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_FAVORITE_GAME = "favorite_game"
    }
}