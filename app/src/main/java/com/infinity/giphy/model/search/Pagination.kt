package com.infinity.giphy.model.search

import java.io.Serializable

data class Pagination(
    val count: Int,
    val offset: Int,
    val total_count: Int
) : Serializable