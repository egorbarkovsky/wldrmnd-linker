package com.wldrmnd.smlr.service

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.math.abs

class DefaultKeyConverterServiceTest {

    val service : KeyConverterService = DefaultKeyConverterService()
    @Test
    fun givenIdMustBeConverableBothWays() {
        val rand = Random()
        for (i in 0..1000L) {
            val initialId = abs(rand.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            assertEquals(initialId, id)
        }
    }
}