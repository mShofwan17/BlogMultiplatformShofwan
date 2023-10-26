package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaPlus
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import me.learn.blogmultiplatformshofwan.components.AdminPageLayout
import me.learn.blogmultiplatformshofwan.components.LoadingIndicator
import me.learn.blogmultiplatformshofwan.models.RandomJoke
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.navigation.Screen
import me.learn.blogmultiplatformshofwan.utils.constant.Constant
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.SIDE_PANEL_WIDTH
import me.learn.blogmultiplatformshofwan.utils.constant.ResConst
import me.learn.blogmultiplatformshofwan.utils.fetchRandomJoke
import me.learn.blogmultiplatformshofwan.utils.isUserLoggedIn
import me.learn.blogmultiplatformshofwan.utils.largerThanMD
import me.learn.blogmultiplatformshofwan.utils.sizeBreakpoint
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    var randomJoke: RandomJoke? by remember { mutableStateOf(null) }

    LaunchedEffect(
        key1 = Unit
    ) {
        fetchRandomJoke {
            randomJoke = it
        }
    }

    AdminPageLayout {
        HomeContent(randomJoke = randomJoke)
        AddButton()
    }
}

@Composable
fun HomeContent(randomJoke: RandomJoke?) {
    val breakpoint = rememberBreakpoint()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                left = sizeBreakpoint(
                    breakpoint.largerThanMD(),
                    positive = SIDE_PANEL_WIDTH
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        randomJoke?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(topBottom = 50.px),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (randomJoke.id != -1) {
                    Image(
                        modifier = Modifier
                            .size(150.px)
                            .margin(bottom = 50.px),
                        src = ResConst.Image.laugh,
                        desc = "Laugh Image"
                    )

                    if (randomJoke.joke.contains("Q:")) {
                        SpanText(
                            modifier = Modifier.fillMaxWidth(60.percent)
                                .margin(bottom = 40.px)
                                .textAlign(TextAlign.Center)
                                .color(Theme.Secondary.rgb)
                                .fontSize(28.px)
                                .fontFamily(Constant.FONT_ARIAL_FAMILY)
                                .fontWeight(FontWeight.Bold),
                            text = randomJoke.joke.split(":").first()
                        )

                        SpanText(
                            modifier = Modifier.fillMaxWidth(60.percent)
                                .margin(bottom = 40.px)
                                .textAlign(TextAlign.Center)
                                .color(Theme.HalfBlack.rgb)
                                .fontSize(20.px)
                                .fontFamily(Constant.FONT_ARIAL_FAMILY)
                                .fontWeight(FontWeight.Normal),
                            text = randomJoke.joke.split(":").last()
                        )
                    } else {
                        SpanText(
                            modifier = Modifier.fillMaxWidth(60.percent)
                                .margin(bottom = 40.px)
                                .textAlign(TextAlign.Center)
                                .color(Theme.Secondary.rgb)
                                .fontSize(28.px)
                                .fontFamily(Constant.FONT_ARIAL_FAMILY)
                                .fontWeight(FontWeight.Bold),
                            text = randomJoke.joke.split(":").first()
                        )
                    }
                } else {
                    LoadingIndicator()
                }
            }
        }
    }
}

@Composable
fun AddButton() {
    val breakpoint = rememberBreakpoint()
    val context = rememberPageContext()
    Box(
        modifier = Modifier
            .height(100.vh)
            .fillMaxWidth()
            .maxWidth(Constant.PAGE_WIDTH.px)
            .position(Position.Fixed)
            .styleModifier {
                property("pointer-events", "none")
            },
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .margin(
                    right = sizeBreakpoint(
                        condition = breakpoint.largerThanMD(),
                        positive = 40,
                        negative = 20
                    ),
                    bottom = sizeBreakpoint(
                        condition = breakpoint.largerThanMD(),
                        positive = 40,
                        negative = 20
                    ),
                )
                .size(
                    sizeBreakpoint(
                        condition = breakpoint.largerThanMD(),
                        positive = 80,
                        negative = 40
                    )
                )
                .backgroundColor(Theme.PrimaryColor.rgb)
                .borderRadius(r = 14.px)
                .cursor(Cursor.Pointer)
                .onClick {
                    context.router.navigateTo(Screen.Admin.Create.route)
                }
                .styleModifier {
                    property("pointer-events", "auto")
                },
            contentAlignment = Alignment.Center
        ) {
            FaPlus(
                modifier = Modifier.color(Colors.White),
                size = IconSize.LG
            )
        }
    }
}