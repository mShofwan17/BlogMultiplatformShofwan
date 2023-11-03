package me.learn.blogmultiplatformshofwan.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.document
import kotlinx.browser.localStorage
import me.learn.blogmultiplatformshofwan.models.ControlStyle
import me.learn.blogmultiplatformshofwan.models.EditorControl
import me.learn.blogmultiplatformshofwan.navigation.Screen
import me.learn.blogmultiplatformshofwan.utils.constant.IdConst
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.get
import org.w3c.dom.set

@Composable
fun isUserLoggedIn(content: @Composable () -> Unit) {
    val context = rememberPageContext()
    val remembered = remember { localStorage["remember"].toBoolean() }
    val userId = remember { localStorage["userId"] }
    var userIdExist by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        userIdExist = if (!userId.isNullOrEmpty()) checkUserId(id = userId) else false
        if (!remembered || !userIdExist) {
            context.router.navigateTo(Screen.Admin.Login.route)
        }
    }

    if (remembered && userIdExist) content()
    else println("Loading..")

}

fun sizeBreakpoint(
    condition: Boolean,
    positive: Int = 0,
    negative: Int = 0,
): CSSSizeValue<CSSUnit.px> {
    return if (condition) positive.px
    else negative.px
}

fun logout() {
    localStorage["remember"] = false.toString()
    localStorage["userId"] = ""
    localStorage["username"] = ""
}

fun getEditor() = document.getElementById(IdConst.InputType.editor) as HTMLTextAreaElement
fun getSelectedIntRange(): IntRange? {
    val editor = getEditor()
    val start = editor.selectionStart
    val end = editor.selectionEnd

    return if (start != null && end != null) {
        IntRange(start, (end - 1))
    } else null
}

fun getSelectedText(): String? {
    val range = getSelectedIntRange()
    return if (range != null) {
        getEditor().value.substring(range)
    } else null
}

fun applyStyle(controlStyle: ControlStyle) {
    val selectedText = getSelectedText()
    val selectedIntRange = getSelectedIntRange()

    if (selectedIntRange != null && selectedText != null) {
        getEditor().value = getEditor().value.replaceRange(
            range = selectedIntRange,
            replacement = controlStyle.style
        )

        document.getElementById(IdConst.InputType.editor)?.innerHTML = getEditor().value
    }
}

fun applyControlStyle(
    editorControl: EditorControl,
    onLinkClick: () -> Unit,
    onImageClick: () -> Unit
) {
    when (editorControl) {
        EditorControl.Bold -> {
            applyStyle(
                ControlStyle.Bold(
                    selectedText = getSelectedText()
                )
            )
        }

        EditorControl.Italic -> {
            applyStyle(
                ControlStyle.Italic(
                    selectedText = getSelectedText()
                )
            )
        }

        EditorControl.Quote -> {
            applyStyle(
                ControlStyle.Quote(
                    selectedText = getSelectedText()
                )
            )
        }

        EditorControl.Subtitle -> {
            applyStyle(
                ControlStyle.Subtitle(
                    selectedText = getSelectedText()
                )
            )
        }

        EditorControl.Title -> {
            applyStyle(
                ControlStyle.Title(
                    selectedText = getSelectedText()
                )
            )
        }

        EditorControl.Link -> {
            onLinkClick()
        }

        EditorControl.Photograph -> {
            onImageClick()
        }

        EditorControl.Code -> {
            applyStyle(
                ControlStyle.Code(
                    selectedText = getSelectedText()
                )
            )
        }
    }
}