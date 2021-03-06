package com.wldrmnd.smlr.service

import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertEquals as assertEquals

class DefaultKeyMapperServiceTest {

    @InjectMocks
    private val service : KeyMapperService = DefaultKeyMapperService()

    @Mock
    lateinit var converter: KeyConverterService

    private val KEY_A: String = "abc"
    private val KEY_B: String = "cde"
    private val ID_A: Long = 10000000L
    private val ID_B: Long = 10000001L

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(converter.keyToId(KEY_A)).thenReturn(ID_A)
        Mockito.`when`(converter.idToKey(ID_A)).thenReturn(KEY_A)
        Mockito.`when`(converter.keyToId(KEY_B)).thenReturn(ID_B)
        Mockito.`when`(converter.idToKey(ID_B)).thenReturn(KEY_B)
    }


    private val LINK_A: String = "http://www.google.com"
    private val LINK_B: String = "http://www.yahoo.com"

    @Test
    fun clientCanAddLinks() {
        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A),
                service.getLink(keyA))

        val keyB = service.add(LINK_B)
        assertEquals(KeyMapperService.Get.Link(LINK_B),
                service.getLink(keyB))

        assertNotEquals(keyA, keyB)
    }


    private val KEY: String = "aAbBcCdD"

    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFountInService() {
        assertEquals(KeyMapperService.Get.NotFound(KEY),
                service.getLink(KEY))
    }
}