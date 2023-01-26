package com.example.rawgbootcampidn.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.rawgbootcampidn.data.LocalDataSource
import com.example.rawgbootcampidn.data.RemoteDataSource
import com.example.rawgbootcampidn.data.Repository
import com.example.rawgbootcampidn.data.database.GameDatabase
import com.example.rawgbootcampidn.data.database.GameEntity
import com.example.rawgbootcampidn.data.network.Service
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.model.GameDetail
import com.example.rawgbootcampidn.model.Screenshots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    // API
    private val gameService = Service.GameService
    private val remote = RemoteDataSource(gameService)

    // LOCAL
    private val gameDao = GameDatabase.getDatabase(application).gameDao()
    private val local = LocalDataSource(gameDao)

    private val repository = Repository(remote,local)

    private var _gameDetail: MutableLiveData<NetworkResult<GameDetail>> = MutableLiveData()
    val gameDetail: LiveData<NetworkResult<GameDetail>> = _gameDetail

    private var _screenshotsGame: MutableLiveData<NetworkResult<Screenshots>> = MutableLiveData()
    val screenshotsGame: LiveData<NetworkResult<Screenshots>> = _screenshotsGame

    fun fetchGameDetail(gameId: Int) {
        viewModelScope.launch {
            repository.remote!!.getGameDetailById(gameId).collect { result ->
                _gameDetail.value = result
            }
        }
    }

    fun fetchScreenshotsGame(gameId: Int) {
        viewModelScope.launch {
            repository.remote!!.getGameScreenshotById(gameId).collect { result ->
                _screenshotsGame.value = result
            }
        }
    }

    val favoriteGameList:LiveData<List<GameEntity>> = repository.local!!.listGame().asLiveData()
    fun insertFavoriteGame(gameEntity: GameEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.local!!.insertGame(gameEntity)
        }
    }

    fun deleteFavoriteGame(gameEntity: GameEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.local!!.deleteGame(gameEntity)
        }
    }




}