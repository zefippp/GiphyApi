package com.infinity.giphy.model.search

import java.io.Serializable

data class FixedWidthDownsampled(
    val height: String,
    val size: String,
    val url: String,
    val webp: String,
    val webp_size: String,
    val width: String
) : Serializable