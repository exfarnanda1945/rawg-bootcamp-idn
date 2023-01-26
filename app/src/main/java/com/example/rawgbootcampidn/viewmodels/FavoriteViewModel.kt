package com.example.rawgbootcampidn.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.rawgbootcampidn.data.LocalDataSource
import com.example.rawgbootcampidn.data.Repository
import com.example.rawgbootcampidn.data.database.GameDatabase
import com.example.rawgbootcampidn.data.database.GameEntity

class FavoriteViewModel(application: Application):AndroidViewModel(application) {
    // LOCAL
    private val gameDao = GameDatabase.getDatabase(application).gameDao()
    private val local = LocalDataSource(gameDao)

    private val repository = Repository(local = local)

    val favoriteGameList: LiveData<List<GameEntity>> = repository.local!!.listGame().asLiveData()
}