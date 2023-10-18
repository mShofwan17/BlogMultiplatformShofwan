package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import me.learn.blogmultiplatformshofwan.utils.isUserLoggedIn
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomeScreen() {
    isUserLoggedIn {
        HomePage()
    }
}

@Composable
fun HomePage() {
    Text("Admin Home Page")
}