package com.wldrmnd.smlr.repo

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.wldrmnd.smlr.model.AbstractRepositoryTest
import com.wldrmnd.smlr.model.Link
import com.wldrmnd.smlr.model.repo.LinkRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import org.hamcrest.Matchers.*
import org.hamcrest.MatcherAssert.assertThat

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL,
                  value = [LinkRepositoryTest.DATASET])
class LinkRepositoryTest : AbstractRepositoryTest() {

    @Autowired
    lateinit var repository : LinkRepository

    @Test
    fun findOneExisting() {
        val got : Optional<Link> = repository.findById(LINK_1_ID)
        assertThat(got.isPresent, equalTo(true))
        val link = got.get();
        assertThat(link, equalTo(Link(LINK_1_TEXT, LINK_1_ID)))
    }

    @Test
    fun findOneNotExisting() {
        val got : Optional<Link> = repository.findById(LINK_NOT_FOUND)
        assertThat(got.isPresent, equalTo(false))
    }

    @Test
    fun saveNew() {
        val toBeSaved = Link(LINK_TBS_TEXT)
        val got : Link = repository.save(toBeSaved)
        val list : List<Link> = repository.findAll()

        assertThat(list, hasSize(4))
        assertThat(got.text, equalTo(LINK_TBS_TEXT))
    }

    companion object {
        const val DATASET = "classpath:datasets/link-table.xml"

        private const val LINK_1_ID: Long = 100500L
        private const val LINK_1_TEXT: String = "http://www.eveonline.com"

        private const val LINK_TBS_TEXT: String = "http://www.ru"
        private const val LINK_NOT_FOUND: Long = 1L
    }
}