package com.redbox.anchan.util

import org.jsoup.Jsoup

fun cleanHtml(html: String) : String?{
    return Jsoup.parse(html).text()
}

