package com.infinity.giphy.model.search

import java.io.Serializable

data class Meta(
    val msg: String,
    val response_id: String,
    val status: Int
) : Serializable