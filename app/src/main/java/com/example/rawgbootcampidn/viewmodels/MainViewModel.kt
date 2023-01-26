package com.example.rawgbootcampidn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawgbootcampidn.data.RemoteDataSource
import com.example.rawgbootcampidn.data.Repository
import com.example.rawgbootcampidn.data.network.Service
import com.example.rawgbootcampidn.data.network.handler.NetworkResult
import com.example.rawgbootcampidn.model.Game
import com.example.rawgbootcampidn.utils.Constant.QUERY_DATES
import com.example.rawgbootcampidn.utils.Constant.QUERY_ORDERING
import com.example.rawgbootcampidn.utils.Constant.QUERY_PAGE_SIZE
import kotlinx.coroutines.launch
import kotlin.collections.set

class MainViewModel() : ViewModel() {

    // API
    private val gameService = Service.GameService
    private val remote = RemoteDataSource(gameService)

    private val repository = Repository(remote)

    private var _listGame: MutableLiveData<NetworkResult<Game>> = MutableLiveData()
    val listGame: LiveData<NetworkResult<Game>> = _listGame

    init {
        fetchListGame()
    }

    private fun fetchListGame() {
        viewModelScope.launch {
            repository.remote?.getGameList(applyQueries())?.collect { result ->
                _listGame.value = result
            }
        }
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_PAGE_SIZE] = "20"
        queries[QUERY_ORDERING] = "-added"
        queries[QUERY_DATES] = "2022-01-01,2022-12-31"

        return queries
    }
}