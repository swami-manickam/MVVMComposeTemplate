package com.compose.mvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.compose.mvvm.data.local.entity.MvvmComposeSources
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceDao {

    @Transaction
    @Query("SELECT * FROM Sources")
    fun getSourcesNews(): Flow<List<MvvmComposeSources>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSourcesNews(newsSource: List<MvvmComposeSources>): List<Long>

    @Query("DELETE FROM Sources")
    fun clearSourcesNews(): Int

    @Transaction
    fun deleteAndInsertAllSourceNews(newsSource: List<MvvmComposeSources>): List<Long> {
        clearSourcesNews()
        return insertSourcesNews(newsSource)
    }
}