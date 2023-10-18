package me.learn.blogmultiplatformshofwan.utils

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import me.learn.blogmultiplatformshofwan.models.User
import me.learn.blogmultiplatformshofwan.models.UserSafe
import kotlinx.serialization.encodeToString

suspend fun checkUserExistence(user: User): UserSafe? {
    return try {
        val result = window.api.tryPost(
            apiPath = "user-check",
            body = Json.encodeToString(user).encodeToByteArray()
        )

        Json.decodeFromString<UserSafe>(result.toString())
    } catch (e: Exception){
        println(e.message)
        null
    }
}