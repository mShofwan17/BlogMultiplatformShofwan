package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Resize
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.file.loadDataUrlFromDisk
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.disabled
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.resize
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Switch
import com.varabyte.kobweb.silk.components.forms.SwitchSize
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.document
import me.learn.blogmultiplatformshofwan.components.AdminPageLayout
import me.learn.blogmultiplatformshofwan.models.Category
import me.learn.blogmultiplatformshofwan.models.EditorKey
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.styles.EditorKeyStyle
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.FONT_ARIAL_FAMILY
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.SIDE_PANEL_WIDTH
import me.learn.blogmultiplatformshofwan.utils.constant.IdConst
import me.learn.blogmultiplatformshofwan.utils.isUserLoggedIn
import me.learn.blogmultiplatformshofwan.utils.largerThanMD
import me.learn.blogmultiplatformshofwan.utils.noBorder
import me.learn.blogmultiplatformshofwan.utils.sizeBreakpoint
import me.learn.blogmultiplatformshofwan.utils.smallerThanSM
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextArea
import org.jetbrains.compose.web.dom.Ul

data class CreatePageUiEvent(
    var id: String = "",
    var title: String = "",
    var subtitle: String = "",
    var thumbnail: String = "",
    var thumbnailInputDisabled: Boolean = true,
    var content: String = "",
    var category: Category = Category.Programming,
    var main: Boolean = false,
    var popular: Boolean = false,
    var sponsored: Boolean = false,
    var editorVisibility: Boolean = true,
)

@Page
@Composable
fun CreatePage() {
    isUserLoggedIn {
        CreateScreen()
    }
}

@Composable
fun CreateScreen() {
    val breakpoint = rememberBreakpoint()
    var uiEvent by remember {
        mutableStateOf(
            CreatePageUiEvent()
        )
    }

    AdminPageLayout {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .margin(topBottom = 50.px)
                .padding(
                    left = sizeBreakpoint(
                        condition = breakpoint.largerThanMD(),
                        positive = SIDE_PANEL_WIDTH
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .maxWidth(700.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleGrid(
                    numColumns = numColumns(base = 1, sm = 3)
                ) {
                    Row(
                        modifier = Modifier
                            .margin(
                                right = sizeBreakpoint(
                                    condition = breakpoint.smallerThanSM(),
                                    negative = 24
                                ),
                                bottom = sizeBreakpoint(
                                    condition = breakpoint.smallerThanSM(),
                                    positive = 12
                                )
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            modifier = Modifier
                                .margin(right = 8.px),
                            checked = uiEvent.popular,
                            onCheckedChange = { uiEvent = uiEvent.copy(popular = it) },
                            size = SwitchSize.LG
                        )
                        SpanText(
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontFamily(FONT_ARIAL_FAMILY)
                                .color(Theme.HalfBlack.rgb),
                            text = "Popular"
                        )
                    }

                    Row(
                        modifier = Modifier
                            .margin(
                                right = sizeBreakpoint(
                                    breakpoint.smallerThanSM(),
                                    negative = 24
                                ),
                                bottom = sizeBreakpoint(
                                    breakpoint.smallerThanSM(),
                                    positive = 12
                                )
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            modifier = Modifier
                                .margin(right = 8.px),
                            checked = uiEvent.main,
                            onCheckedChange = { uiEvent = uiEvent.copy(main = it) },
                            size = SwitchSize.LG
                        )
                        SpanText(
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontFamily(FONT_ARIAL_FAMILY)
                                .color(Theme.HalfBlack.rgb),
                            text = "Main"
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            modifier = Modifier
                                .margin(right = 8.px),
                            checked = uiEvent.sponsored,
                            onCheckedChange = { uiEvent = uiEvent.copy(sponsored = it) },
                            size = SwitchSize.LG
                        )
                        SpanText(
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontFamily(FONT_ARIAL_FAMILY)
                                .color(Theme.HalfBlack.rgb),
                            text = "Sponsored"
                        )
                    }
                }

                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .fillMaxWidth()
                        .height(54.px)
                        .margin(topBottom = 12.px)
                        .padding(leftRight = 20.px)
                        .backgroundColor(Theme.LightGreyColor.rgb)
                        .borderRadius(r = 4.px)
                        .noBorder()
                        .fontFamily(FONT_ARIAL_FAMILY)
                        .fontSize(16.px)
                        .toAttrs {
                            attr("placeholder", "Title")
                        }
                )


                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .fillMaxWidth()
                        .height(54.px)
                        .margin(bottom = 12.px)
                        .padding(leftRight = 20.px)
                        .backgroundColor(Theme.LightGreyColor.rgb)
                        .borderRadius(r = 4.px)
                        .noBorder()
                        .fontFamily(FONT_ARIAL_FAMILY)
                        .fontSize(16.px)
                        .toAttrs {
                            attr("placeholder", "Subtitle")
                        }
                )

                CategoryDropdown(
                    selectedCategory = uiEvent.category,
                    onCategorySelect = {
                        uiEvent = uiEvent.copy(category = it)
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(topBottom = 12.px),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Switch(
                        modifier = Modifier
                            .margin(right = 8.px),
                        checked = !uiEvent.thumbnailInputDisabled,
                        onCheckedChange = { uiEvent = uiEvent.copy(thumbnailInputDisabled = !it) },
                        size = SwitchSize.MD
                    )
                    SpanText(
                        modifier = Modifier
                            .fontSize(14.px)
                            .fontFamily(FONT_ARIAL_FAMILY)
                            .color(Theme.HalfBlack.rgb),
                        text = "Paste an Image URL instead"
                    )
                }

                ThumbnailUploader(
                    thumbnail = uiEvent.thumbnail,
                    thumbnailInputDisabled = uiEvent.thumbnailInputDisabled,
                    onThumbnailSelect = { filename, _ ->
                        println(filename)
                        uiEvent = uiEvent.copy(thumbnail = filename)
                    }
                )

                EditorControls(
                    breakpoint = breakpoint,
                    editorVisibility = uiEvent.editorVisibility,
                    onEditorVisibilityChange = {
                        uiEvent = uiEvent.copy(editorVisibility = !uiEvent.editorVisibility)
                    }
                )
                Editor(editorVisibility = uiEvent.editorVisibility)
                CreateButton(
                    onCreateClicked = {

                    }
                )
            }
        }
    }
}

@Composable
fun CategoryDropdown(
    selectedCategory: Category,
    onCategorySelect: (Category) -> Unit
) {
    Box(
        modifier = Modifier
            .margin(bottom = 12.px)
            .classNames("dropdown")
            .fillMaxWidth()
            .height(54.px)
            .backgroundColor(Theme.LightGreyColor.rgb)
            .cursor(Cursor.Pointer)
            .attrsModifier {
                attr("data-bs-toggle", "dropdown")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(leftRight = 20.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SpanText(
                modifier = Modifier
                    .fillMaxWidth()
                    .fontSize(16.px)
                    .fontFamily(FONT_ARIAL_FAMILY),
                text = selectedCategory.name
            )
            Box(
                modifier = Modifier.classNames("dropdown-toggle")
            )
        }

        Ul(
            attrs = Modifier
                .fillMaxWidth()
                .classNames("dropdown-menu")
                .toAttrs()
        ) {
            Category.values().forEach { category ->
                Li {
                    A(
                        attrs = Modifier
                            .classNames("dropdown-item")
                            .color(Colors.Black)
                            .fontFamily(FONT_ARIAL_FAMILY)
                            .fontSize(16.px)
                            .onClick { onCategorySelect(category) }
                            .toAttrs()
                    ) {
                        Text(
                            value = category.name
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThumbnailUploader(
    thumbnail: String,
    thumbnailInputDisabled: Boolean,
    onThumbnailSelect: (String, String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .margin(bottom = 20.px)
            .height(54.px)
    ) {
        Input(
            type = InputType.Text,
            attrs = Modifier
                .fillMaxSize()
                .margin(bottom = 12.px, right = 12.px)
                .padding(leftRight = 20.px)
                .backgroundColor(Theme.LightGreyColor.rgb)
                .borderRadius(r = 4.px)
                .noBorder()
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(16.px)
                .thenIf(
                    condition = thumbnailInputDisabled,
                    other = Modifier.disabled()
                )
                .toAttrs {
                    attr("placeholder", "Thumbnail")
                    attr("value", thumbnail)
                }
        )

        Button(
            attrs = Modifier
                .fillMaxHeight()
                .padding(leftRight = 24.px)
                .backgroundColor(if (!thumbnailInputDisabled) Theme.Gray.rgb else Theme.PrimaryColor.rgb)
                .color(if (!thumbnailInputDisabled) Theme.DarkGray.rgb else Theme.White.rgb)
                .noBorder()
                .borderRadius(r = 4.px)
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontWeight(FontWeight.Medium)
                .fontSize(14.px)
                .thenIf(
                    condition = !thumbnailInputDisabled,
                    other = Modifier.disabled()
                )
                .onClick {
                    document.loadDataUrlFromDisk(
                        accept = "image/png, image/jpeg",
                        onLoaded = {
                            onThumbnailSelect(filename, it)
                        }
                    )
                }
                .toAttrs()
        ) {
            SpanText(text = "Upload")
        }
    }
}

@Composable
fun EditorControls(
    breakpoint: Breakpoint,
    editorVisibility: Boolean,
    onEditorVisibilityChange: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        SimpleGrid(
            modifier = Modifier.fillMaxWidth(),
            numColumns = numColumns(base = 1, sm = 2)
        ) {
            Row(
                modifier = Modifier
                    .height(54.px)
                    .backgroundColor(Theme.LightGreyColor.rgb)
            ) {
                EditorKey.values().forEach {
                    EditorKeyView(it)
                }
            }

            Box(
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    attrs = Modifier
                        .height(54.px)
                        .thenIf(
                            condition = breakpoint.smallerThanSM(),
                            other = Modifier.fillMaxWidth()
                        )
                        .margin(
                            topBottom = sizeBreakpoint(
                                condition = breakpoint.smallerThanSM(),
                                positive = 12
                            )
                        )
                        .padding(leftRight = 24.px)
                        .borderRadius(4.px)
                        .backgroundColor(
                            if (editorVisibility) Theme.LightGreyColor.rgb
                            else Theme.PrimaryColor.rgb
                        )
                        .color(
                            if (editorVisibility) Theme.DarkGray.rgb
                            else Theme.White.rgb
                        )
                        .cursor(Cursor.Pointer)
                        .noBorder()
                        .onClick {
                            onEditorVisibilityChange()
                        }
                        .toAttrs()
                ) {
                    SpanText(
                        modifier = Modifier.fontFamily(FONT_ARIAL_FAMILY)
                            .fontWeight(FontWeight.Medium)
                            .fontSize(14.px),
                        text = "Preview"
                    )
                }
            }
        }
    }
}


@Composable
fun EditorKeyView(key: EditorKey) {
    Box(
        modifier = EditorKeyStyle.toModifier()
            .fillMaxHeight()
            .padding(leftRight = 12.px)
            .borderRadius(r = 4.px)
            .cursor(Cursor.Pointer)
            .onClick { },
        contentAlignment = Alignment.Center
    ) {
        Image(
            src = key.icon,
            desc = "${key.icon} Icon"
        )
    }
}

@Composable
fun Editor(
    editorVisibility: Boolean
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextArea(
            attrs = Modifier
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(16.px)
                .id(IdConst.InputType.editor)
                .fillMaxWidth()
                .height(400.px)
                .maxHeight(400.px)
                .margin(topBottom = 8.px)
                .padding(all = 20.px)
                .backgroundColor(Theme.LightGreyColor.rgb)
                .borderRadius(4.px)
                .resize(Resize.None)
                .noBorder()
                .visibility(
                    if (editorVisibility) Visibility.Visible else Visibility.Hidden
                )
                .toAttrs {
                    attr("placeholder", "Type here....")
                }
        )
        Div(
            attrs = Modifier
                .id(IdConst.InputType.editorPreview)
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(16.px)
                .fillMaxWidth()
                .height(400.px)
                .maxHeight(400.px)
                .margin(topBottom = 8.px)
                .padding(all = 20.px)
                .backgroundColor(Theme.LightGreyColor.rgb)
                .borderRadius(4.px)
                .resize(Resize.None)
                .noBorder()
                .visibility(
                    if (editorVisibility) Visibility.Hidden else Visibility.Visible
                )
                .overflow(Overflow.Auto)
                .scrollBehavior(ScrollBehavior.Smooth)
                .toAttrs()
        ) {

        }
    }
}

@Composable
fun CreateButton(
    onCreateClicked: () -> Unit
) {
    Button(
        attrs = Modifier
            .fillMaxWidth()
            .height(54.px)
            .margin(top = 8.px)
            .backgroundColor(Theme.PrimaryColor.rgb)
            .color(Colors.White)
            .noBorder()
            .borderRadius(r = 4.px)
            .fontFamily(FONT_ARIAL_FAMILY)
            .onClick {
                onCreateClicked()
            }
            .toAttrs()
    ) {
        SpanText("Create Post")
    }
}