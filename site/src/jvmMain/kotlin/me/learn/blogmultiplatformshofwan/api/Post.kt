package me.learn.blogmultiplatformshofwan.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.learn.blogmultiplatformshofwan.data.MongoDB
import me.learn.blogmultiplatformshofwan.models.ApiListResponse
import me.learn.blogmultiplatformshofwan.models.Post
import org.litote.kmongo.id.ObjectIdGenerator

@Api(routeOverride = "add_post")
suspend fun addPost(
    context: ApiContext
) {
    try {
        val post = context.req.body?.decodeToString()?.let {
            Json.decodeFromString<Post>(it)
        }
        val newPost = post?.copy(id = ObjectIdGenerator.newObjectId<String>().id.toHexString())
        context.res.setBodyText(
            newPost?.let {
                context.data.getValue<MongoDB>().addPost(it).toString()
            } ?: false.toString()
        )
    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(e.message))
    }
}

@Api(routeOverride = "list_post")
suspend fun readMyPost(
    context: ApiContext
) {
    try {
        val skip = context.req.params["skip"]?.toInt() ?: 0
        val author = context.req.params["author"] ?: ""

        val result = context.data.getValue<MongoDB>().readMyPost(skip = skip, author = author)
        context.res.setBodyText(
            Json.encodeToString(ApiListResponse.Success(result))
        )

    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(ApiListResponse.Error(message = e.message.toString())))
    }
}
