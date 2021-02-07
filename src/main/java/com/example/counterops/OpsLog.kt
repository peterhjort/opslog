package com.example.counterops

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class OpsLogEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timestamp: Long,
    val amount: Int,
    val note: String
    )

@Dao
interface OpsLogDAO {
    @Insert
    suspend fun insert(entry: OpsLogEntry)
    @Query("select * from OpsLogEntry")
    fun getAll(): LiveData<List<OpsLogEntry>>
    @Query("select * from OpsLogEntry where timestamp > :timestamp")
    fun getAllSince(timestamp: Long): LiveData<List<OpsLogEntry>>
}