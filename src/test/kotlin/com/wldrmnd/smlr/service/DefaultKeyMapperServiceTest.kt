package com.wldrmnd.smlr.service

import org.junit.Test
import org.junit.Assert.assertEquals as assertEquals

class DefaultKeyMapperServiceTest {

    private val service : KeyMapperService = DefaultKeyMapperService()

    private val KEY: String = "aAbBcCdD"
    private val LINK: String = "http://www.vk.com"
    private val LINK_NEW: String = "http://www.wow.ru"

    @Test
    fun clientCanAddNewKeyWithLink() {
        assertEquals(KeyMapperService.Add.Success(KEY, LINK),
                service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK),
                service.getLink(KEY))
    }

    @Test
    fun clientCanNotAddExistingKey() {
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY),
                service.add(KEY, LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK),
                service.getLink(KEY))
    }

    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFountInService() {
        assertEquals(KeyMapperService.Get.NotFound(KEY),
                service.getLink(KEY))
    }
}