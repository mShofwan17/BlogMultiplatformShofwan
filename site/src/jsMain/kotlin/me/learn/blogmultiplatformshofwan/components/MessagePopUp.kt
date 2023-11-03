package me.learn.blogmultiplatformshofwan.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
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
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import me.learn.blogmultiplatformshofwan.models.EditorControl
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.FONT_ARIAL_FAMILY
import me.learn.blogmultiplatformshofwan.utils.constant.IdConst
import me.learn.blogmultiplatformshofwan.utils.noBorder
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.css.vw
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLInputElement

@Composable
fun Popup(
    message: String,
    onDialogDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.vw)
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(19),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .backgroundColor(Theme.HalfBlack.rgb)
                .onClick { onDialogDismiss() }
        )
        Box(
            modifier = Modifier.padding(all = 24.px)
                .backgroundColor(Colors.White)
                .borderRadius(r = 4.px)
        ) {
            SpanText(
                modifier = Modifier.fillMaxWidth()
                    .textAlign(TextAlign.Center)
                    .fontFamily(FONT_ARIAL_FAMILY)
                    .fontSize(16.px),
                text = message
            )
        }
    }
}

@Composable
fun ControlPopUp(
    editorControl: EditorControl,
    onDialogDismiss: () -> Unit,
    onAddClick: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.vw)
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(19),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .backgroundColor(Theme.HalfBlack.rgb)
                .onClick { onDialogDismiss() }
        )
        Column(
            modifier = Modifier
                .width(500.px)
                .padding(all = 24.px)
                .backgroundColor(Colors.White)
                .borderRadius(r = 4.px)
        ) {
            Input(
                type = InputType.Text,
                attrs = Modifier
                    .id(IdConst.InputType.linkHref)
                    .fillMaxWidth()
                    .height(54.px)
                    .borderRadius(r = 4.px)
                    .padding(left = 20.px)
                    .margin(bottom = 12.px)
                    .fontFamily(FONT_ARIAL_FAMILY)
                    .fontSize(14.px)
                    .noBorder()
                    .backgroundColor(Theme.LightGreyColor.rgb)
                    .toAttrs {
                        attr(
                            "placeholder", if (editorControl == EditorControl.Link)
                                "Href" else "Image Url"
                        )
                    }
            )

            Input(
                type = InputType.Text,
                attrs = Modifier
                    .id(IdConst.InputType.linkTitle)
                    .fillMaxWidth()
                    .height(54.px)
                    .borderRadius(r = 4.px)
                    .padding(left = 20.px)
                    .margin(bottom = 20.px)
                    .fontFamily(FONT_ARIAL_FAMILY)
                    .fontSize(14.px)
                    .noBorder()
                    .backgroundColor(Theme.LightGreyColor.rgb)
                    .toAttrs {
                        attr(
                            "placeholder", if (editorControl == EditorControl.Link)
                                "Title" else "Description"
                        )
                    }
            )

            Button(
                attrs = Modifier
                    .onClick {
                        val href =
                            (document.getElementById(IdConst.InputType.linkHref) as HTMLInputElement).value
                        val title =
                            (document.getElementById(IdConst.InputType.linkTitle) as HTMLInputElement).value

                        onAddClick(href, title)
                        onDialogDismiss()
                    }
                    .fillMaxWidth()
                    .height(54.px)
                    .borderRadius(r = 4.px)
                    .backgroundColor(Theme.PrimaryColor.rgb)
                    .color(Colors.White)
                    .fontFamily(FONT_ARIAL_FAMILY)
                    .fontSize(14.px)
                    .noBorder()
                    .toAttrs()
            ) {
                SpanText(text = "Add")
            }
        }
    }
}