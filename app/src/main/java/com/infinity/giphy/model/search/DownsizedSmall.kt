package com.infinity.giphy.model.search

import java.io.Serializable

data class DownsizedSmall(
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val width: String
) : Serializable