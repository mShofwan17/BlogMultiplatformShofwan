package me.learn.blogmultiplatformshofwan.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import me.learn.blogmultiplatformshofwan.utils.Constant
import org.jetbrains.compose.web.css.px

@Composable
fun AdminPageLayout(
    content: @Composable () -> Unit
) {

    var overflowMenuOpen by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .maxWidth(Constant.PAGE_WIDTH.px)
        ) {
            SidelPanel(
                onMenuClick = {
                    overflowMenuOpen = true
                }
            )
            if (overflowMenuOpen){
                OverflowSidePanel {
                    overflowMenuOpen = false
                }
            }
        }

        content.invoke()
    }
}