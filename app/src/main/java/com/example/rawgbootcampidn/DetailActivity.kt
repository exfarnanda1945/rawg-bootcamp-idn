package com.example.rawgbootcampidn

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawgbootcampidn.adapter.ScreenshotGameAdapter
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.databinding.ActivityDetailBinding
import com.example.rawgbootcampidn.model.GameResult
import com.example.rawgbootcampidn.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private val screenshotAdapter by lazy { ScreenshotGameAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val game = intent.getParcelableExtra<GameResult>(EXTRA_GAME)

        detailViewModel.fetchGameDetail(game?.id!!)
        detailViewModel.fetchScreenshotsGame(game.id)


        detailViewModel.screenshotsGame.observe(this){result ->
            if(result is NetworkResult.Success){
                binding.screenshotsGame.apply {
                    adapter = screenshotAdapter
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(
                        this@DetailActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    screenshotAdapter.setData(result.data?.results)
                }
            }
        }
        detailViewModel.gameDetail.observe(this) { result ->
            when (result) {
                is NetworkResult.Loading -> handleUi(
                    layoutWrapper = false,
                    progressBar = true,
                    errorTv = false,
                    errorImg = false
                )
                is NetworkResult.Error -> {
                    handleUi(
                        layoutWrapper = false,
                        progressBar = false,
                        errorTv = true,
                        errorImg = true
                    )
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Success -> {
                    binding.gameDetail = result.data
                    handleUi(
                        layoutWrapper = true,
                        progressBar = false,
                        errorTv = false,
                        errorImg = false
                    )
                }
            }
        }


    }

    private fun handleUi(
        layoutWrapper: Boolean,
        progressBar: Boolean,
        errorTv: Boolean,
        errorImg: Boolean
    ) {
        binding.apply {
            gameDetailWrapper.isVisible = layoutWrapper
            progressbar.isVisible = progressBar
            errorIcon.isVisible = errorImg
            errorText.isVisible = errorTv
        }
    }

    companion object {
        const val EXTRA_GAME = "game"
    }
}