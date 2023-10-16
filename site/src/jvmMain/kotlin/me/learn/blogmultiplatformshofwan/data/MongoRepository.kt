package me.learn.blogmultiplatformshofwan.data

import me.learn.blogmultiplatformshofwan.models.User

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
}