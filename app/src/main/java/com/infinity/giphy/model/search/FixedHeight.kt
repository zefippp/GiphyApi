package com.infinity.giphy.model.search

import java.io.Serializable

data class FixedHeight(
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val size: String,
    val url: String,
    val webp: String,
    val webp_size: String,
    val width: String
) : Serializable