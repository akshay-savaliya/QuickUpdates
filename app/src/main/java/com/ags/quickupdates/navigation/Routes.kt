package com.ags.quickupdates.navigation

import android.net.Uri

object Routes {
    const val SPLASH = "splash"
    const val AUTH = "auth"
    const val HOME = "home"
    const val ARTICLE = "article/{url}"
    const val SEARCH = "search"
    const val SETTINGS = "settings"
}

fun articleRoute(url: String): String = "article/${Uri.encode(url)}"