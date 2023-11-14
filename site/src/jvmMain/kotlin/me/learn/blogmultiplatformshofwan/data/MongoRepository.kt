package me.learn.blogmultiplatformshofwan.data

import me.learn.blogmultiplatformshofwan.models.ListPost
import me.learn.blogmultiplatformshofwan.models.Post
import me.learn.blogmultiplatformshofwan.models.User

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
    suspend fun checkUserId(id: String): Boolean
    suspend fun addPost(post: Post): Boolean
    suspend fun readMyPost(skip: Int, author: String): List<ListPost>
}