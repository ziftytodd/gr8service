package com.zifty

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import groovy.json.JsonSlurper

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

    @HystrixCommand(fallbackMethod = "defaultChuck")
    @RequestMapping("/chuck")
    @ResponseBody
    public String chuck() {
        // Thanks to Ken Kousen for this code
        String base = 'http://api.icndb.com/jokes/random?'
        String qs = [limitTo: '[nerdy]'].collect { k,v -> "$k=$v" }.join('&')
        String jsonTxt = "$base$qs".toURL().getText(requestProperties: ['User-Agent':''])
        def json = new JsonSlurper().parseText(jsonTxt)
        return json?.value?.joke
    }

    public String defaultChuck() {
        return "Chuck Norris never fails";
    }
}

