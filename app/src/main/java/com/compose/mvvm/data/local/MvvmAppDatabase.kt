package com.compose.mvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.mvvm.data.local.dao.SourceDao
import com.compose.mvvm.data.local.dao.TopHeadlinesDao
import com.compose.mvvm.data.local.entity.Article
import com.compose.mvvm.data.local.entity.MvvmComposeSources

@Database(
    entities = [Article::class, MvvmComposeSources::class],
    version = 1,
    exportSchema = false
)
abstract class MvvmAppDatabase : RoomDatabase() {

    abstract fun topHeadlinesDao(): TopHeadlinesDao

    abstract fun newsSourceDao(): SourceDao

}