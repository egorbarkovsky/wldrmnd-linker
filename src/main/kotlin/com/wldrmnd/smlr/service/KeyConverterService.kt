package com.wldrmnd.smlr.service

interface KeyConverterService {

    fun keyToId(key: String): Long

    fun idToKey(id: Long): String
}
