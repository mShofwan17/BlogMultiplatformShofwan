package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import me.learn.blogmultiplatformshofwan.components.AdminPageLayout
import me.learn.blogmultiplatformshofwan.components.SearchBar
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.SIDE_PANEL_WIDTH
import me.learn.blogmultiplatformshofwan.utils.isUserLoggedIn
import me.learn.blogmultiplatformshofwan.utils.largerThanMD
import me.learn.blogmultiplatformshofwan.utils.sizeBreakpoint
import me.learn.blogmultiplatformshofwan.utils.sizeBreakpointPercent
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun PostsPage() {
    isUserLoggedIn {
        PostsScreen()
    }
}

@Composable
fun PostsScreen() {
    val breakpoint = rememberBreakpoint()

    AdminPageLayout {
        Column(
            modifier = Modifier.fillMaxSize()
                .margin(top = 50.px)
                .padding(
                    left = sizeBreakpoint(
                        condition = breakpoint.largerThanMD(),
                        positive = SIDE_PANEL_WIDTH,
                    )
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(
                        sizeBreakpointPercent(
                            condition = breakpoint.largerThanMD(),
                            positive = 30,
                            negative = 60
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                SearchBar(
                    onEnterClick = {

                    }
                )
            }
        }
    }
}