package com.compose.mvvm.data.local


import com.compose.mvvm.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {


    fun getSourceNewsByDB(sourceID: String): Flow<List<Article>>

    fun deleteAllAndInsertAllSourceNews(articles: List<Article>, sourceID: String)

}