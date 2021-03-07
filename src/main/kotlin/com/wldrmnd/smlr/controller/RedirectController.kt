package com.wldrmnd.smlr.controller

import com.wldrmnd.smlr.service.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping
class RedirectController {

    @Autowired
    lateinit var service : KeyMapperService


    @GetMapping
    fun home() = "home"

    @GetMapping("/{key}")
    fun redirect(@PathVariable("key") key : String,
                 response : HttpServletResponse) {

        when (val result = service.getLink(key)) {
            is KeyMapperService.Get.Link -> {
                response.setHeader(HEADER_NAME, result.link)
                response.status = 302
            }
            is KeyMapperService.Get.NotFound -> {
                response.status = 404
            }
        }
    }


    companion object {

        private const val HEADER_NAME = "Location"
    }
}
