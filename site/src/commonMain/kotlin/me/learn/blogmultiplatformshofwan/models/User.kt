package me.learn.blogmultiplatformshofwan.models

expect class User {
    val id: String
    val username: String
    val password: String
}

expect class UserSafe {
    val id: String
    val username: String
}