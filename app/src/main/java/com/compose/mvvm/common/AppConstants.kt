package com.compose.mvvm.common

object AppConstants {

    const val APP_NAME = "NewsApp"
    const val API_KEY = "8094a578ebb841239f30392d9e0fb066"
    const val COUNTRY = "us"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val DATABASE_NAME = "news-database"
    const val INITIAL_PAGE = 1
    const val PAGE_SIZE = 10
    const val DEBOUNCE_TIMEOUT = 300L
    const val MIN_SEARCH_CHAR = 3
    const val NEWS_BY_SOURCES = "sources"
    const val NEWS_BY_COUNTRY = "country"
    const val NEWS_BY_LANGUAGE = "language"
    const val SOURCE_ID = "sourceId"
    const val COUNTRY_ID = "countryId"
    const val LANGUAGE_ID = "languageId"

    //WorkManager and Notification
    const val UNIQUE_WORK_NAME = "newsAppPeriodicWork"
    const val MORNING_UPDATE_TIME = 5
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CHANNEL_ID = "news_channel"
    const val NOTIFICATION_CHANNEL_NAME = "News"
    const val NOTIFICATION_CONTENT_TITLE = "News"
    const val NOTIFICATION_CONTENT_TEXT = "Check out the latest news ..."

}