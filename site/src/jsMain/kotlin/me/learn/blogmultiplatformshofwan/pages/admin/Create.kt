package me.learn.blogmultiplatformshofwan.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.varabyte.kobweb.core.rememberPageContext
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
import kotlinx.browser.localStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.learn.blogmultiplatformshofwan.components.AdminPageLayout
import me.learn.blogmultiplatformshofwan.components.ControlPopUp
import me.learn.blogmultiplatformshofwan.components.Popup
import me.learn.blogmultiplatformshofwan.models.Category
import me.learn.blogmultiplatformshofwan.models.ControlStyle
import me.learn.blogmultiplatformshofwan.models.EditorControl
import me.learn.blogmultiplatformshofwan.models.Post
import me.learn.blogmultiplatformshofwan.models.Theme
import me.learn.blogmultiplatformshofwan.navigation.Screen
import me.learn.blogmultiplatformshofwan.styles.EditorKeyStyle
import me.learn.blogmultiplatformshofwan.utils.addPost
import me.learn.blogmultiplatformshofwan.utils.applyControlStyle
import me.learn.blogmultiplatformshofwan.utils.applyStyle
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.FONT_ARIAL_FAMILY
import me.learn.blogmultiplatformshofwan.utils.constant.Constant.SIDE_PANEL_WIDTH
import me.learn.blogmultiplatformshofwan.utils.constant.IdConst
import me.learn.blogmultiplatformshofwan.utils.getEditor
import me.learn.blogmultiplatformshofwan.utils.getSelectedText
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
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.get
import kotlin.js.Date

data class CreatePageUiState(
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
    var messagePopup: Boolean = false,
    var linkPopUp: Boolean = false,
    var imagePopUp: Boolean = false
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
    val context = rememberPageContext()
    var uiState by remember {
        mutableStateOf(
            CreatePageUiState()
        )
    }
    val scope = rememberCoroutineScope()

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
                            checked = uiState.popular,
                            onCheckedChange = { uiState = uiState.copy(popular = it) },
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
                            checked = uiState.main,
                            onCheckedChange = { uiState = uiState.copy(main = it) },
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
                            checked = uiState.sponsored,
                            onCheckedChange = { uiState = uiState.copy(sponsored = it) },
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
                        .id(IdConst.InputType.title)
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
                        .id(IdConst.InputType.subtitle)
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
                    selectedCategory = uiState.category,
                    onCategorySelect = {
                        uiState = uiState.copy(category = it)
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
                        checked = !uiState.thumbnailInputDisabled,
                        onCheckedChange = { uiState = uiState.copy(thumbnailInputDisabled = !it) },
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
                    thumbnail = uiState.thumbnail,
                    thumbnailInputDisabled = uiState.thumbnailInputDisabled,
                    onThumbnailSelect = { filename, file ->
                        (document.getElementById(IdConst.InputType.thumbnail) as HTMLInputElement).value =
                            filename
                        uiState = uiState.copy(thumbnail = file)
                    }
                )

                EditorControls(
                    breakpoint = breakpoint,
                    editorVisibility = uiState.editorVisibility,
                    onLinkClick = {
                        uiState = uiState.copy(
                            linkPopUp = true
                        )
                    },
                    onEditorVisibilityChange = {
                        uiState = uiState.copy(editorVisibility = !uiState.editorVisibility)
                    },
                    onImageClick = {
                        uiState = uiState.copy(imagePopUp = true)
                    }
                )
                Editor(editorVisibility = uiState.editorVisibility)
                CreateButton(
                    onCreateClicked = {
                        scope.launch {
                            uiState =
                                uiState.copy(title = (document.getElementById(IdConst.InputType.title) as HTMLInputElement).value)
                            uiState =
                                uiState.copy(subtitle = (document.getElementById(IdConst.InputType.subtitle) as HTMLInputElement).value)
                            uiState =
                                uiState.copy(content = (document.getElementById(IdConst.InputType.editor) as HTMLTextAreaElement).value)

                            if (!uiState.thumbnailInputDisabled) {
                                uiState =
                                    uiState.copy(content = (document.getElementById(IdConst.InputType.thumbnail) as HTMLInputElement).value)
                            }

                            if (
                                uiState.title.isNotEmpty() &&
                                uiState.subtitle.isNotEmpty() &&
                                uiState.thumbnail.isNotEmpty() &&
                                uiState.content.isNotEmpty()
                            ) {
                                val post = localStorage["username"]?.let { author ->
                                    Post(
                                        authorName = author,
                                        title = uiState.title,
                                        subtitle = uiState.subtitle,
                                        date = Date.now().toLong(),
                                        thumbnail = uiState.thumbnail,
                                        detail = uiState.content,
                                        category = uiState.category,
                                        popular = uiState.popular,
                                        main = uiState.main,
                                        sponsored = uiState.sponsored
                                    )
                                }

                                post?.let {
                                    val result = addPost(it)
                                    if (result) context.router.navigateTo(Screen.Admin.Success.route)
                                }

                            } else {
                                uiState = uiState.copy(messagePopup = true)
                                delay(2000)
                                uiState = uiState.copy(messagePopup = false)
                            }
                        }
                    }
                )
            }
        }
    }

    if (uiState.messagePopup) {
        Popup(
            message = "Please fill out all fields.",
            onDialogDismiss = { uiState = uiState.copy(messagePopup = false) }
        )
    }

    if (uiState.linkPopUp) {
        ControlPopUp(
            editorControl = EditorControl.Link,
            onDialogDismiss = {
                uiState = uiState.copy(linkPopUp = false)
            },
            onAddClick = { href, title ->
                applyStyle(
                    ControlStyle.Link(
                        selectedText = getSelectedText(),
                        href = href,
                        title = title
                    )
                )
            }
        )
    }

    if (uiState.imagePopUp) {
        ControlPopUp(
            editorControl = EditorControl.Photograph,
            onDialogDismiss = {
                uiState = uiState.copy(imagePopUp = false)
            },
            onAddClick = { imageUrl, description ->
                applyStyle(
                    ControlStyle.Image(
                        selectedText = getSelectedText(),
                        imageUrl = imageUrl,
                        desc = description
                    )
                )
            }
        )
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
                .id(IdConst.InputType.thumbnail)
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
    onLinkClick: () -> Unit,
    onImageClick:() -> Unit,
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
                EditorControl.values().forEach {
                    EditorControlView(
                        it,
                        onClick = {
                            applyControlStyle(
                                editorControl = it,
                                onLinkClick = onLinkClick,
                                onImageClick = onImageClick
                            )
                        }
                    )
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
                            document.getElementById(IdConst.InputType.editorPreview)?.innerHTML =
                                getEditor().value
                            js("hljs.highlightAll()") as Unit
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
fun EditorControlView(
    key: EditorControl,
    onClick: () -> Unit
) {
    Box(
        modifier = EditorKeyStyle.toModifier()
            .fillMaxHeight()
            .padding(leftRight = 12.px)
            .borderRadius(r = 4.px)
            .cursor(Cursor.Pointer)
            .onClick { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            src = key.icon,
            desc = "${key.icon} Icon"
        )
    }
}

@Composable
fun Editor(editorVisibility: Boolean) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextArea(
            attrs = Modifier
                .id(IdConst.InputType.editor)
                .fillMaxWidth()
                .height(400.px)
                .maxHeight(400.px)
                .resize(Resize.None)
                .margin(top = 8.px)
                .padding(all = 20.px)
                .backgroundColor(Theme.LightGreyColor.rgb)
                .borderRadius(r = 4.px)
                .noBorder()
                .visibility(
                    if (editorVisibility) Visibility.Visible
                    else Visibility.Hidden
                )
                .fontFamily(FONT_ARIAL_FAMILY)
                .fontSize(16.px)
                .toAttrs {
                    attr("placeholder", "Type here...")
                }
        )
        Div(
            attrs = Modifier
                .id(IdConst.InputType.editorPreview)
                .fillMaxWidth()
                .height(400.px)
                .maxHeight(400.px)
                .margin(top = 8.px)
                .padding(all = 20.px)
                .backgroundColor(Theme.LightGreyColor.rgb)
                .borderRadius(r = 4.px)
                .visibility(
                    if (editorVisibility) Visibility.Hidden
                    else Visibility.Visible
                )
                .overflow(Overflow.Auto)
                .scrollBehavior(ScrollBehavior.Smooth)
                .noBorder()
                .toAttrs()
        )
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