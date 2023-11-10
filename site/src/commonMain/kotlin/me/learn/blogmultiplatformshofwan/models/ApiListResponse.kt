package me.learn.blogmultiplatformshofwan.models

expect sealed class ApiListResponse{
    object Idle
    class Success
    class Error
}