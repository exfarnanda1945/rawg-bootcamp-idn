package com.example.rawgbootcampidn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawgbootcampidn.adapter.GameAdapter
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.databinding.ActivityMainBinding
import com.example.rawgbootcampidn.model.GameResult
import com.example.rawgbootcampidn.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val gameAdapter by lazy { GameAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mainViewModel.listGame.observe(this) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    handleUi(
                        recyclerView = false,
                        progressBar = true,
                        errorTv = false,
                        errorImg = false
                    )
                }
                is NetworkResult.Error -> {
                    binding.errorText.text = result.errorMessage
                    handleUi(
                        recyclerView = false,
                        progressBar = false,
                        errorTv = true,
                        errorImg = true
                    )
                    Toast.makeText(this,result.errorMessage,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Success -> {
                    binding.rvBestGame.apply {
                        adapter = gameAdapter
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        gameAdapter.setData(result.data?.results)
                    }
                    gameAdapter.setOnItemClickCallback(object : GameAdapter.IOnItemCallBack {
                        override fun onItemClickCallback(data: GameResult) {
                            val intent = Intent(this@MainActivity,DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_GAME,data)
                            startActivity(intent)
                        }

                    })
                    handleUi(
                        recyclerView = true,
                        progressBar = false,
                        errorTv = false,
                        errorImg = false
                    )
                }
            }
        }
    }


    private fun handleUi(
        recyclerView: Boolean,
        progressBar: Boolean,
        errorTv: Boolean,
        errorImg: Boolean
    ) {
        binding.apply {
            rvBestGame.isVisible = recyclerView
            progressbar.isVisible = progressBar
            errorIcon.isVisible = errorImg
            errorText.isVisible = errorTv
        }
    }
}