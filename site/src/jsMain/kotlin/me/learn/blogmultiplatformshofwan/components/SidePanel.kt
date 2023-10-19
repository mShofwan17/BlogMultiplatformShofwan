package me.learn.blogmultiplatformshofwan.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.dom.Path
import com.varabyte.kobweb.compose.dom.Svg
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.utils.Constant.FONT_ARIAL_FAMILY
import me.learn.blogmultiplatformshofwan.utils.Constant.SIDE_PANEL_WIDTH
import me.learn.blogmultiplatformshofwan.utils.ResConst
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun SidePanel() {
    Column(
        modifier = Modifier
            .padding(leftRight = 40.px, topBottom = 50.px)
            .width(SIDE_PANEL_WIDTH.px)
            .height(100.vh)
            .position(Position.Fixed)
            .backgroundColor(Theme.Secondary.rgb)
            .zIndex(9)
    ) {
        Image(
            modifier = Modifier.margin(bottom = 60.px),
            src = ResConst.Image.logo,
            desc = "Logo Image"
        )
        SpanText(
            modifier = Modifier
                .margin(bottom = 30.px)
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(14.px)
                .color(Theme.HalfWhite.rgb),
            text = "Dashboard"
        )

        NavigationItem(
            modifier = Modifier.margin(bottom = 24.px),
            selected = true,
            title = "Home",
            icon = ResConst.PathIcon.home
        ) {}
        NavigationItem(
            modifier = Modifier.margin(bottom = 24.px),
            title = "Create Post",
            icon = ResConst.PathIcon.add_post
        ) {}
        NavigationItem(
            modifier = Modifier.margin(bottom = 24.px),
            title = "My Posts",
            icon = ResConst.PathIcon.posts
        ) {}
        NavigationItem(
            title = "Logout",
            icon = ResConst.PathIcon.logout
        ) {}
    }
}

@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .cursor(Cursor.Pointer)
            .onClick { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier.margin(right = 10.px),
            pathData = icon,
            color = if (selected) Theme.PrimaryColor.hex else Theme.HalfWhite.hex
        )
        SpanText(
            modifier = Modifier
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(16.px)
                .color(
                    if (selected) Theme.PrimaryColor.rgb else Theme.White.rgb
                ),
            text = title
        )
    }
}

@Composable
fun VectorIcon(
    modifier: Modifier = Modifier,
    pathData: String,
    color: String
) {
    Svg(
        attrs = modifier
            .width(24.px)
            .height(24.px).toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {
        Path {
            attr("d", pathData)
            attr("stroke", color)
            attr("stroke-width", "2")
            attr("stroke-line-cap", "round")
            attr("stroke-line-join", "round")
        }
    }
}