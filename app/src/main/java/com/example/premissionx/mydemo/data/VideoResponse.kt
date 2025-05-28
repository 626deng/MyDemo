package com.example.premissionx.mydemo.data

data class VideoResponse(
    val error_code: Int,
    val reason: String,
    val result: List<Result>
)

data class Result(
    val author: String,
    val comment_count: Int,
    val digg_count: Int,
    val hot_value: Int,
    val hot_words: String,
    val item_cover: String,
    val play_count: Int,
    val share_url: String,
    val title: String
)