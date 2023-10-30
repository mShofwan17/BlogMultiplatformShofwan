package me.learn.blogmultiplatformshofwan.navigation

sealed class Screen(val route: String){

    object Admin{
        data object Login: Screen("/admin/login")
        data object Home: Screen("/admin/")
        data object Create: Screen("/admin/create")
        data object Posts: Screen("/admin/posts")
        data object Success: Screen("/admin/success")
    }

}
