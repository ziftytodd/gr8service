package com.zifty

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by todd on 7/21/16.
 */
@Controller
@RefreshScope
class Gr8Controller {
    @Value("\${greeting}")
    private String greeting;

    @RequestMapping("/")
    @ResponseBody
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        "${greeting} there, ${name}!"
    }
}

