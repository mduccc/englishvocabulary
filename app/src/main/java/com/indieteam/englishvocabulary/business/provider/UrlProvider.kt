package com.indieteam.englishvocabulary.business.provider

object UrlProvider{
    class Yandex {
        companion object {
            val baseUrl = "https://onlineTranslate.yandex.net"
            val format = "plain"
            val lang = "en-vi"
            val key = APIKey.Yandex.key
        }
    }
}