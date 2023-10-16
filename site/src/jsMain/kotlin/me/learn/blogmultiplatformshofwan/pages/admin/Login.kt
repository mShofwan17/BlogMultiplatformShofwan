package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.utils.Constant
import me.learn.blogmultiplatformshofwan.utils.ResConst
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input

@Page
@Composable
fun LoginScreen() {
    val errorText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(leftRight = 50.px, top = 80.px, bottom = 24.px)
                .backgroundColor(Theme.LightGreyColor.rgb),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(100.px)
                    .margin(bottom = 50.px),
                src = ResConst.Image.logo,
                desc = "logo image"
            )

            Input(
                type = InputType.Text,
                attrs = Modifier
                    .width(350.px)
                    .height(54.px)
                    .padding(leftRight = 20.px)
                    .margin(bottom = 12.px)
                    .backgroundColor(Colors.White)
                    .fontFamily(Constant.FONT_ARIAL_FAMILY)
                    .border {
                        width(0.px)
                        style(LineStyle.None)
                        color(Colors.Transparent)
                    }
                    .outline(
                        width = 1.5.px,
                        style = LineStyle.Inset,
                        color = Theme.PrimaryColor.rgb
                    )
                    .toAttrs {
                        attr("placeholder", "Username")
                    }
            )

            Input(
                type = InputType.Password,
                attrs = Modifier
                    .width(350.px)
                    .height(54.px)
                    .padding(leftRight = 20.px)
                    .margin(top = 8.px, bottom = 20.px)
                    .fontFamily(Constant.FONT_ARIAL_FAMILY)
                    .backgroundColor(Colors.White)
                    .border {
                        width(0.px)
                        style(LineStyle.None)
                        color(Colors.Transparent)
                    }
                    .outline(
                        width = 1.5.px,
                        style = LineStyle.Inset,
                        color = Theme.PrimaryColor.rgb
                    )
                    .toAttrs {
                        attr("placeholder", "Password")
                    }
            )

            Button(
                attrs = Modifier
                    .width(350.px)
                    .height(50.px)
                    .backgroundColor(Theme.PrimaryColor.rgb)
                    .borderRadius(r = 4.px)
                    .fontFamily(Constant.FONT_ARIAL_FAMILY)
                    .fontWeight(FontWeight.Bold)
                    .fontSize(16.px)
                    .color(Colors.White)
                    .border(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .outline(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .toAttrs()
            ) {
                SpanText(text = "Masuk")
            }

            SpanText(
                modifier = Modifier
                    .width(50.px)
                    .color(Colors.Red)
                    .margin(top = 24.px)
                    .textAlign(TextAlign.Center),
                text = errorText
            )

        }
    }
}