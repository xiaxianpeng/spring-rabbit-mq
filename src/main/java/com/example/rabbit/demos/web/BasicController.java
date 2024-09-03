/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.rabbit.demos.web;

import com.example.rabbit.demos.service.delayed.DelayedMessageSender;
import com.example.rabbit.demos.service.dlx.DLXSender;
import com.example.rabbit.demos.service.standard.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private DelayedMessageSender delayedMessageSender;

    @Autowired
    private DLXSender dlxSender;

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/sender?message=hello
    @RequestMapping("/sender")
    @ResponseBody
    public String sender(@RequestParam("message") String message) {
        messageSender.sendMessage(message);
        return "Send: " + message;
    }

    // http://127.0.0.1:8080/sendDelayedMessage?delay=20
    @RequestMapping("/sendDelayedMessage")
    @ResponseBody
    public String sendDelayedMessage(@RequestParam("delay") Integer delay) {
        long msg = System.currentTimeMillis();
        delayedMessageSender.sendDelayedMessage(msg + "", delay);
        return "Send: " + msg;
    }

    // http://127.0.0.1:8080/sendDlx
    @RequestMapping("/sendDlx")
    @ResponseBody
    public String sendDlx() {
        long msg = System.currentTimeMillis();
        dlxSender.sendDelayedMessage(msg + "");
        return "Send: " + msg;
    }
}
