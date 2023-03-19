package com.example.youtube_api.data.remote.model
data class Playlists(
    val item: String,
    val tokenPage: String,
    val dataInit: DataInit,
    val tag: String,
    val items: List<Items>,
)
data class High(
    val height: Int,
    val url: String,
    val width: Int
)
data class Maxres(
    val height: Int,
    val url: String,
    val width: Int
)
data class Medium(
    val height: Int,
    val url: String,
    val width: Int
)
data class Standard(
    val height: Int,
    val url: String,
    val width: Int
)
data class itemList(
    val items: List<Items>?,
    val item: String?,
    val tag: String?,
    val snippet: Snippet?
)
data class Items(
    val contentDetails: ContentDetails,
    val id: String,
    val item: String,
    val tag: String,
    val snippet: Snippet
)
data class DataInit(
    val resultTotal: Int,
    val resultPage: Int,
)
data class ContentDetails(
    val itemCount: Int
)
data class Snippet(
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val channelId: String,
    val channelTitle: String,
    val title: String
)
data class Localized(
    val description: String,
    val title: String
)
data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxres,
    val medium: Medium,
    val standard: Standard
)
data class Default(
    val height: Int,
    val url: String,
    val width: Int
)
