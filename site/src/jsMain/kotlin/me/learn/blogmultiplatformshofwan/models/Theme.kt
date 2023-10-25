package me.learn.blogmultiplatformshofwan.models

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.css.rgba

enum class Theme(
    val hex: String,
    val rgb: CSSColorValue
) {
    PrimaryColor(
        hex = "#00A2FF",
        rgb = rgb(r = 0, g = 162, b = 255)
    ),
    Secondary(
        hex = "#001019",
        rgb = rgb(r = 0, g = 16, b = 25)
    ),
    LightGreyColor(
        hex = "#FAFAFA",
        rgb = rgb(r = 250, g = 250, b = 250)
    ),
    HalfWhite(
        hex = "#FFFFFF",
        rgb = rgba(r = 255, g = 255, b = 255, a = 0.5)
    ),
    White(
        hex = "#FFFFFF",
        rgb = rgb(r = 255, g = 255, b = 255)
    ),
    HalfBlack(
        hex = "#000000",
        rgb = rgba(r = 0, g = 0, b = 0, a = 0.5)
    ),
    Green(
        hex = "#00FF94",
        rgb = rgb(r = 255, g = 255, b = 148)
    ),
    Yellow(
        hex = "#FFEC45",
        rgb = rgb(r = 255, g = 236, b = 69)
    ),
    Purple(
        hex = "#FF6359",
        rgb = rgb(r = 255, g = 99, b = 89)
    ),

    Gray(
    hex = "#E9E9E9",
    rgb = rgb(r = 233, g = 233, b = 233)
    ),

    DarkGray(
        hex = "#646464",
        rgb = rgb(r = 100, g = 100, b = 100)
    ),
}