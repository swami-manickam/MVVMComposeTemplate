package com.compose.mvvm.data.local

import com.compose.mvvm.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

class AppDatabaseService constructor(private val appDatabase: MvvmAppDatabase) : DatabaseService {


    override fun getSourceNewsByDB(sourceID: String): Flow<List<Article>> {
        return appDatabase.topHeadlinesDao().getSourceArticle(sourceID)
    }

    override fun deleteAllAndInsertAllSourceNews(articles: List<Article>, sourceID: String) {
        appDatabase.topHeadlinesDao().deleteAllAndInsertAllSourceArticles(articles, sourceID)
    }



}