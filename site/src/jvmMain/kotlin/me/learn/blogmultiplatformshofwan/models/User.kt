package me.learn.blogmultiplatformshofwan.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.ObjectIdGenerator

@Serializable
data class User(
    @SerialName(value = "_id")
    val id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    val username: String = "",
    val password: String = ""
)

@Serializable
data class UserSafe(
    @SerialName(value = "_id")
    val id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    val username: String = "",
)
