package com.wldrmnd.smlr.service

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class DefaultKeyMapperService : KeyMapperService {

    private val map: MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String) : KeyMapperService.Add {
        return if (map.containsKey(key)) {
            KeyMapperService.Add.AlreadyExist(key)
        } else {
            map.putIfAbsent(key, link)
            KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String) = if (map.contains(key)) {
        KeyMapperService.Get.Link(map[key]!!)
    } else {
        KeyMapperService.Get.NotFound(key);
    }
}