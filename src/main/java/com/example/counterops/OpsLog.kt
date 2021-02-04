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
    fun insert(entry: OpsLogEntry)
    @Query("select * from OpsLogEntry")
    fun getAll(): LiveData<List<OpsLogEntry>>
}