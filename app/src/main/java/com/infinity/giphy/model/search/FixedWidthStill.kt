package com.infinity.giphy.model.search

import java.io.Serializable

data class FixedWidthStill(
    val height: String,
    val size: String,
    val url: String,
    val width: String
) : Serializable