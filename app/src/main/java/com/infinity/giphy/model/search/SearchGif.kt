package com.infinity.giphy.model.search

data class SearchGif(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)