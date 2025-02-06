package com.compose.mvvm.data.repository


import com.compose.mvvm.data.local.DatabaseService
import com.compose.mvvm.data.local.entity.Article
import com.compose.mvvm.data.model.toArticleEntity
import com.compose.mvvm.data.network.ApiService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
class MvvmComposeRepository @Inject constructor(
    private val networkService: ApiService,
    private val databaseService: DatabaseService
) {

    fun getNewsBySources(sourceId: String): Flow<List<Article>> {
        return flow { emit(networkService.getNewsBySources(sourceId)) }
            .map {
                it.apiArticles.map { apiArticle -> apiArticle.toArticleEntity(sourceId) }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAllSourceNews(articles, sourceId)) }
            }.flatMapConcat {
                databaseService.getSourceNewsByDB(sourceId)
            }
    }

    fun getNewsBySourceByDB(sourceId: String): Flow<List<Article>> {
        return databaseService.getSourceNewsByDB(sourceId)
    }

}