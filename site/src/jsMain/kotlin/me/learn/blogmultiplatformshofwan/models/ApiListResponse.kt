package me.learn.blogmultiplatformshofwan.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
actual sealed class ApiListResponse {
    @Serializable
    @SerialName("idle")
    actual data object Idle : ApiListResponse()
    @Serializable
    @SerialName("success")
    actual data class Success(val data: List<ListPost>) : ApiListResponse()
    @Serializable
    @SerialName("error")
    actual data class Error(val message: String) : ApiListResponse()
}