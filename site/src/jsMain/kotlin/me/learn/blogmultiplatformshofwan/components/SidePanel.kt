package me.learn.blogmultiplatformshofwan.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.dom.Path
import com.varabyte.kobweb.compose.dom.Svg
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.navigation.Screen
import me.learn.blogmultiplatformshofwan.styles.NavigationItemStyle
import me.learn.blogmultiplatformshofwan.utils.Constant.COLLAPSED_PANEL_HEIGHT
import me.learn.blogmultiplatformshofwan.utils.Constant.FONT_ARIAL_FAMILY
import me.learn.blogmultiplatformshofwan.utils.Constant.SIDE_PANEL_WIDTH
import me.learn.blogmultiplatformshofwan.utils.IdConst
import me.learn.blogmultiplatformshofwan.utils.ResConst
import me.learn.blogmultiplatformshofwan.utils.logout
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun SidelPanel(onMenuClick: () -> Unit) {
    val breakpoint = rememberBreakpoint()
    if (breakpoint > Breakpoint.MD) SidePanelInternal()
    else CollapsedSidePanel { onMenuClick.invoke() }

}

@Composable
fun SidePanelInternal() {
    val context = rememberPageContext()

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
        NavigationItems()
    }
}

@Composable
private fun NavigationItems() {
    val context = rememberPageContext()
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
        selected = context.route.path == Screen.Admin.Home.route,
        title = "Home",
        icon = ResConst.PathIcon.home
    ) {
        context.router.navigateTo(Screen.Admin.Home.route)
    }
    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        selected = context.route.path == Screen.Admin.Create.route,
        title = "Create Post",
        icon = ResConst.PathIcon.add_post
    ) {
        context.router.navigateTo(Screen.Admin.Create.route)
    }
    NavigationItem(
        modifier = Modifier.margin(bottom = 24.px),
        selected = context.route.path == Screen.Admin.Posts.route,
        title = "My Posts",
        icon = ResConst.PathIcon.posts
    ) {
        context.router.navigateTo(Screen.Admin.Posts.route)
    }
    NavigationItem(
        title = "Logout",
        icon = ResConst.PathIcon.logout
    ) {
        logout()
        context.router.navigateTo(Screen.Admin.Login.route)
    }
}

@Composable
private fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    Row(
        modifier = NavigationItemStyle.toModifier()
            .then(modifier)
            .cursor(Cursor.Pointer)
            .onClick { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier.margin(right = 10.px),
            selected = selected,
            pathData = icon
            //color = if (selected) Theme.PrimaryColor.hex else Theme.HalfWhite.hex
        )
        SpanText(
            modifier = Modifier
                .id(IdConst.navigationText)
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(16.px)
                .thenIf(
                    condition = selected,
                    other = Modifier.color(Theme.PrimaryColor.rgb)
                ),
            text = title
        )
    }
}

@Composable
private fun VectorIcon(
    modifier: Modifier = Modifier,
    selected: Boolean,
    pathData: String
) {

    Svg(
        attrs = modifier
            .id(IdConst.ImageType.svgParent)
            .width(24.px)
            .height(24.px).toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {

        Path {
            if (selected) {
                attr("style", "stroke: ${Theme.PrimaryColor.hex}")
            }
            attr("id", IdConst.ImageType.vectorIcon)
            attr("d", pathData)
            attr("stroke-width", "2")
            attr("stroke-line-cap", "round")
            attr("stroke-line-join", "round")
        }
    }
}

@Composable
private fun CollapsedSidePanel(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(COLLAPSED_PANEL_HEIGHT.px)
            .padding(24.px)
            .backgroundColor(Theme.Secondary.rgb),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FaBars(
            modifier = Modifier
                .margin(right = 24.px)
                .color(Colors.White)
                .cursor(Cursor.Pointer)
                .onClick { onMenuClick.invoke() },
            size = IconSize.XL
        )
        Image(
            modifier = Modifier.width(80.px),
            src = ResConst.Image.logo,
            desc = "Logo Image"
        )
    }
}

@Composable
fun OverflowSidePanel(
    onMenuClose: () -> Unit
) {
    val breakpoint = rememberBreakpoint()

    Box(
        modifier = Modifier.fillMaxWidth()
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(9)
            .backgroundColor(Theme.HalfBlack.rgb)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
                .width(
                    if (breakpoint < Breakpoint.MD) 50.percent
                    else 25.percent
                )
                .padding(all = 24.px)
                .backgroundColor(Theme.Secondary.rgb)

        ) {
            Row(
                modifier = Modifier
                    .margin(bottom = 60.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FaXmark(
                    modifier = Modifier.margin(right = 20.px)
                        .color(Colors.White)
                        .cursor(Cursor.Pointer)
                        .onClick { onMenuClose.invoke() },
                    size = IconSize.LG
                )
                Image(
                    modifier = Modifier.width(80.px),
                    src = ResConst.Image.logo,
                    desc = "Logo Image"
                )
            }
            NavigationItems()
        }
    }
}