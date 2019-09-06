package com.redbox.anchan.util

import org.jsoup.Jsoup

fun cleanHtml(html: String?): String? {
    return if (html.isNullOrEmpty()) {
        " "
    } else Jsoup.parse(html).text()
}

