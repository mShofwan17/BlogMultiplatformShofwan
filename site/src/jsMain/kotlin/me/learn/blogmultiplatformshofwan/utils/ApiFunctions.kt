package me.learn.blogmultiplatformshofwan.utils

import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.compose.http.http
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.learn.blogmultiplatformshofwan.models.ApiListResponse
import me.learn.blogmultiplatformshofwan.models.Post
import me.learn.blogmultiplatformshofwan.models.RandomJoke
import me.learn.blogmultiplatformshofwan.models.User
import me.learn.blogmultiplatformshofwan.models.UserSafe
import me.learn.blogmultiplatformshofwan.utils.constant.Constant
import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.js.Date

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
    } catch (e: Exception) {
        println(e.message)
        false
    }
}

suspend fun fetchRandomJoke(onComplete: (RandomJoke) -> Unit) {

    val date = localStorage["date"]
    if (date != null) {
        val difference = (Date.now() - date.toDouble())
        val dayHasPassed = difference >= 86400000
        if (dayHasPassed) {
            try {
                val result = window.http.get(Constant.HUMOR_API_URL).decodeToString()
                onComplete(
                    Json.decodeFromString(result)
                )
                localStorage["date"] = Date.now().toString()
                localStorage["joke"] = result
            } catch (e: Exception) {
                onComplete(RandomJoke(id = -1, e.message.toString()))
                println(e.message)
            }
        } else {
            try {
                localStorage["joke"]?.let {
                    onComplete(Json.decodeFromString(it))
                }
            } catch (e: Exception) {
                onComplete(
                    RandomJoke(id = -1, e.message.toString())
                )
                println(e.message)
            }
        }
    } else {
        try {
            val result = window.http.get(Constant.HUMOR_API_URL).decodeToString()
            onComplete(
                Json.decodeFromString(result)
            )
            localStorage["date"] = Date.now().toString()
            localStorage["joke"] = result
        } catch (e: Exception) {
            onComplete(
                RandomJoke(id = -1, e.message.toString())
            )
            println(e.message)
        }
    }
}

suspend fun addPost(post: Post): Boolean {
    return try {
        window.api.tryPost(
            apiPath = "add_post",
            body = Json.encodeToString(post).encodeToByteArray()
        )?.decodeToString().toBoolean()
    } catch (e: Exception) {
        println(e.message.toString())
        false
    }
}

suspend fun fetchListPost(
    skip: Int,
    onSuccess: (ApiListResponse) -> Unit,
    onError: (Exception) -> Unit
) {
    try {
        val result = window.api.tryGet(
            apiPath = "list_post?skip=$skip&author=${localStorage["username"]}"
        )?.decodeToString()

        onSuccess(Json.decodeFromString(result.toString()))
    } catch (e: Exception) {
        onError(e)
    }
}