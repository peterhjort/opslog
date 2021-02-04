package com.example.counterops

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [OpsLogEntry::class], version = 2, exportSchema = false)
abstract class OpsDatabase: RoomDatabase() {
    abstract val opsLogDAO: OpsLogDAO
    companion object {
        @Volatile
        private var INSTANCE: OpsDatabase? = null
        fun getInstance(): OpsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        OpsDatabase::class.java,
                        "ops_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}