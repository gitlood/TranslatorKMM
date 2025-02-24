package com.example.translatorkmm.translate.domain.translate

import com.example.translatorkmm.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String

}