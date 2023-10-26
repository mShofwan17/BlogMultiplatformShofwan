package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.models.User
import me.learn.blogmultiplatformshofwan.models.UserSafe
import me.learn.blogmultiplatformshofwan.navigation.Screen
import me.learn.blogmultiplatformshofwan.styles.loginInputStyle
import me.learn.blogmultiplatformshofwan.utils.checkUserExistence
import me.learn.blogmultiplatformshofwan.utils.constant.Constant
import me.learn.blogmultiplatformshofwan.utils.constant.IdConst.InputType.password
import me.learn.blogmultiplatformshofwan.utils.constant.IdConst.InputType.username
import me.learn.blogmultiplatformshofwan.utils.constant.ResConst
import me.learn.blogmultiplatformshofwan.utils.noBorder
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.set

@Page
@Composable
fun LoginScreen() {
    val scope = rememberCoroutineScope()
    val context = rememberPageContext()
    var errorText by remember { mutableStateOf(" ") }

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
                attrs = loginInputStyle.toModifier()
                    .id(username)
                    .width(350.px)
                    .height(50.px)
                    .padding(leftRight = 20.px)
                    .margin(bottom = 12.px)
                    .backgroundColor(Colors.White)
                    .fontFamily(Constant.FONT_ARIAL_FAMILY)
                    .outline(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
                    )
                    .toAttrs {
                        attr("placeholder", "Username")
                    }
            )

            Input(
                type = InputType.Password,
                attrs = loginInputStyle.toModifier()
                    .id(password)
                    .width(350.px)
                    .height(50.px)
                    .padding(leftRight = 20.px)
                    .margin(top = 8.px, bottom = 20.px)
                    .fontFamily(Constant.FONT_ARIAL_FAMILY)
                    .backgroundColor(Colors.White)
                    .outline(
                        width = 0.px,
                        style = LineStyle.None,
                        color = Colors.Transparent
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
                    .noBorder()
                    .cursor(cursor = Cursor.Pointer)
                    .onClick {
                        scope.launch {
                            val username =
                                (document.getElementById(username) as HTMLInputElement).value
                            val password =
                                (document.getElementById(password) as HTMLInputElement).value

                            if (username.isNotEmpty() && password.isNotEmpty()) {
                                val user = checkUserExistence(
                                    user = User(
                                        username = username,
                                        password = password
                                    )
                                )


                                if (user != null) {
                                    rememberLoggedIn(remember = true, user = user)
                                    context.router.navigateTo(Screen.Admin.Home.route)
                                } else {
                                    errorText = "User does not exist."
                                    delay(3000)
                                    errorText = ""
                                }
                            } else {
                                errorText = "Input fields are empty."
                                delay(3000)
                                errorText = ""
                            }
                        }
                    }
                    .toAttrs()
            ) {
                SpanText(text = "Masuk")
            }

            SpanText(
                modifier = Modifier
                    .fillMaxWidth()
                    .color(Colors.Red)
                    .margin(top = 24.px)
                    .textAlign(TextAlign.Center)
                    .fontFamily(Constant.FONT_ARIAL_FAMILY),
                text = errorText
            )

        }
    }
}

private fun rememberLoggedIn(remember: Boolean, user: UserSafe? = null) {
    localStorage["remember"] = remember.toString()
    if (user != null) {
        localStorage["userId"] = user.id
        localStorage["username"] = user.username
    }
}