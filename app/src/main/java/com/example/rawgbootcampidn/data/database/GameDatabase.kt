package com.example.rawgbootcampidn.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GameTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    
    companion object {
        @Volatile
        private var instance: GameDatabase? = null

        fun getDatabase(ctx: Context): GameDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                instance = tempInstance
            }

            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    ctx.applicationContext, GameDatabase::class.java,
                    "game_database"
                ).build()

                instance = newInstance
                return newInstance
            }
        }
    }


}