package me.learn.blogmultiplatformshofwan.styles

import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.focus
import me.learn.blogmultiplatformshofwan.models.Theme
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val loginInputStyle by ComponentStyle {
    base {
        Modifier.border {
            width(2.px)
            style(LineStyle.Solid)
            color(Colors.Transparent)
        }

    }

    focus {
        Modifier.border {
            width(2.px)
            style(LineStyle.Solid)
            color(Theme.PrimaryColor.rgb)
        }
            .transition(CSSTransition(property = "body", duration = 500.ms))
    }
}