package me.learn.blogmultiplatformshofwan.models

import me.learn.blogmultiplatformshofwan.utils.constant.ResConst

enum class EditorControl(
    val icon: String
) {
    Bold(ResConst.Icon.bold),
    Code(ResConst.Icon.code),
    Italic(ResConst.Icon.italic),
    Link(ResConst.Icon.link),
    Photograph(ResConst.Icon.photograph),
    Quote(ResConst.Icon.quote),
    Subtitle(ResConst.Icon.subtitle),
    Title(ResConst.Icon.title)
}