package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.margin
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Switch
import com.varabyte.kobweb.silk.components.forms.SwitchSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import me.learn.blogmultiplatformshofwan.components.AdminPageLayout
import me.learn.blogmultiplatformshofwan.components.SearchBar
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.FONT_ARIAL_FAMILY
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.SIDE_PANEL_WIDTH
import me.learn.blogmultiplatformshofwan.utils.isUserLoggedIn
import me.learn.blogmultiplatformshofwan.utils.largerThanMD
import me.learn.blogmultiplatformshofwan.utils.noBorder
import me.learn.blogmultiplatformshofwan.utils.sizeBreakpoint
import me.learn.blogmultiplatformshofwan.utils.sizeBreakpointPercent
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button

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
    var selectable by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("Select") }

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
                    ).margin(bottom = 24.px),
                contentAlignment = Alignment.Center
            ) {
                SearchBar(
                    onEnterClick = {

                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(90.percent)
                    .margin(bottom = 24.px),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        modifier = Modifier.margin(right = 8.px),
                        size = SwitchSize.LG,
                        checked = selectable,
                        onCheckedChange = { selectable = it }
                    )
                    SpanText(
                        modifier = Modifier.color(if (selectable) Colors.Black else Theme.HalfBlack.rgb),
                        text = text
                    )
                }
               Button(
                    attrs = Modifier
                        .height(54.px)
                        .padding(leftRight = 24.px)
                        .backgroundColor(Colors.Red)
                        .color(Colors.White)
                        .noBorder()
                        .borderRadius(r = 4.px)
                        .fontFamily(FONT_ARIAL_FAMILY)
                        .fontSize(14.px)
                        .fontWeight(FontWeight.Medium)
                        .onClick {

                        }
                        .toAttrs()
                ){
                   SpanText(text = "Delete")
               }
            }
        }
    }
}