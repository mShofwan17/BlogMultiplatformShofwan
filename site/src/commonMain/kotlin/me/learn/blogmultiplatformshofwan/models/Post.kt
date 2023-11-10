package me.learn.blogmultiplatformshofwan.models

expect class Post {
    val id: String
    val authorName: String
    val date: Long
    val title: String
    val subtitle: String
    val thumbnail: String
    val detail: String
    val category: Category
    val popular: Boolean
    val main: Boolean
    val sponsored: Boolean
}

expect class ListPost {
    val id: String
    val authorName: String
    val date: Long
    val title: String
    val subtitle: String
    val thumbnail: String
    val category: Category
}