package com.example.githubuserapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomData::class], version = 1)
abstract class FavDatabase: RoomDatabase() {

    abstract fun favDao(): FavDao

    companion object{
        @Volatile
        private var INSTANCE: FavDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavDatabase{
            if (INSTANCE == null) {
                synchronized(FavDatabase::class.java) {
                    if (INSTANCE ==  null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            FavDatabase::class.java, "database"
                        )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE as FavDatabase
        }
    }
}