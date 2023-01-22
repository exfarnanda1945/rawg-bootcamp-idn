package com.example.rawgbootcampidn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgbootcampidn.data.RemoteDataSource
import com.example.rawgbootcampidn.data.Repository
import com.example.rawgbootcampidn.data.network.Service
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.model.GameDetail
import com.example.rawgbootcampidn.model.Screenshots
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val gameService = Service.GameService
    private val remote = RemoteDataSource(gameService)
    private val repository = Repository(remote)

    private var _gameDetail: MutableLiveData<NetworkResult<GameDetail>> = MutableLiveData()
    val gameDetail: LiveData<NetworkResult<GameDetail>> = _gameDetail

    private var _screenshotsGame: MutableLiveData<NetworkResult<Screenshots>> = MutableLiveData()
    val screenshotsGame: LiveData<NetworkResult<Screenshots>> = _screenshotsGame

    fun fetchGameDetail(gameId: Int) {
        viewModelScope.launch {
            repository.remote.getGameDetailById(gameId).collect { result ->
                _gameDetail.value = result
            }
        }
    }

    fun fetchScreenshotsGame(gameId: Int) {
        viewModelScope.launch {
            repository.remote.getGameScreenshotById(gameId).collect { result ->
                _screenshotsGame.value = result
            }
        }
    }

}