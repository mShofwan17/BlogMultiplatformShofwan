package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.core.Page
import me.learn.blogmultiplatformshofwan.components.SidePanel
import me.learn.blogmultiplatformshofwan.utils.Constant.PAGE_WIDTH
import me.learn.blogmultiplatformshofwan.utils.isUserLoggedIn
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun HomeScreen() {
    isUserLoggedIn {
        HomePage()
    }
}

@Composable
fun HomePage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .maxWidth(PAGE_WIDTH.px)
        ) {
            SidePanel()
        }
    }
}