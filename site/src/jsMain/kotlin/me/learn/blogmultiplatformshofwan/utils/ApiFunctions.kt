package me.learn.blogmultiplatformshofwan.utils

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.learn.blogmultiplatformshofwan.models.User
import me.learn.blogmultiplatformshofwan.models.UserSafe

suspend fun checkUserExistence(user: User): UserSafe? {
    return try {
        val result = window.api.tryPost(
            apiPath = "user-check",
            body = Json.encodeToString(user).encodeToByteArray()
        )

        result?.decodeToString()?.let { Json.decodeFromString<UserSafe>(it) }
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

suspend fun checkUserId(id: String?): Boolean {
    return try {
        val result = window.api.tryPost(
            apiPath = "check-userid",
            body = Json.encodeToString(id).encodeToByteArray()
        )
        result?.decodeToString()?.let { Json.decodeFromString<Boolean>(it) } ?: false
    }catch (e:Exception){
        println(e.message)
        false
    }
}