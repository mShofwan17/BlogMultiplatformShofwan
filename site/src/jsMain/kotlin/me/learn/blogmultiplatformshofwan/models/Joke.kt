package me.learn.blogmultiplatformshofwan.models

import kotlinx.serialization.Serializable

@Serializable
data class Joke(
    val id: Int,
    val joke: String
)