package com.infinity.giphy.model.search

import java.io.Serializable

data class SearchGif(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
) : Serializable