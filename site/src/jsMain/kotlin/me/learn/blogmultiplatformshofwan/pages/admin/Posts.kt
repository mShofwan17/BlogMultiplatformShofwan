package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import me.learn.blogmultiplatformshofwan.components.AdminPageLayout
import me.learn.blogmultiplatformshofwan.utils.isUserLoggedIn

@Page
@Composable
fun PostsPage() {
    isUserLoggedIn {
        PostsScreen()
    }
}

@Composable
fun PostsScreen() {
    AdminPageLayout {

    }
}