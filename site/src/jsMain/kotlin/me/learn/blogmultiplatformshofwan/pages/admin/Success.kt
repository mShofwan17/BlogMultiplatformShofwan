package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.delay
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.navigation.Screen
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.FONT_ARIAL_FAMILY
import me.learn.blogmultiplatformshofwan.utils.constant.ResConst
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun SuccessPage() {
    val context = rememberPageContext()

    LaunchedEffect(key1 = Unit) {
        delay(5000)
        context.router.navigateTo(Screen.Admin.Create.route)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.margin(bottom = 24.px),
            src = ResConst.Icon.success,
            desc = "Success Icon"
        )
        SpanText(
            modifier = Modifier
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(24.px),
            text = "Post Successfully Created"
        )
        SpanText(
            modifier = Modifier
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(16.px)
                .color(Theme.HalfBlack.rgb),
            text = "Redirecting you back.."
        )
    }
}