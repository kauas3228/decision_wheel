package com.ikaroorg.decision_wheel.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

fun Context.createLocaleContext(
    languageCode: String
): Context {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = Configuration(resources.configuration)
    config.setLocale(locale)

    return createConfigurationContext(config)
}

fun String.toLanguageCode(): String = when (this) {
    "Portuguese" -> "pt"
    "Spanish" -> "es"
    "English" -> "en"
    else -> "en"
}