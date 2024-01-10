/*
 * Copyright 2018 Zhenjie Yan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kcst.sendserver.controller;

import com.alibaba.fastjson.JSON;
import com.kcst.sendserver.model.UserInfo;
import com.kcst.sendserver.util.Logger;
import com.yanzhenjie.andserver.annotation.Addition;
import com.yanzhenjie.andserver.annotation.CookieValue;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestMethod;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.util.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhenjie Yan on 2018/6/9.
 */
@RestController
@RequestMapping(path = "/user")
class TestController {

    @RequestMapping(
        path = "/connection",
        method = {RequestMethod.GET},
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Object getConnection(HttpRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("getLocalAddr", request.getLocalAddr());
        map.put("getLocalName", request.getLocalName());
        map.put("getLocalPort", request.getLocalPort());
        map.put("getRemoteAddr", request.getRemoteAddr());
        map.put("getRemoteHost", request.getRemoteHost());
        map.put("getRemotePort", request.getRemotePort());
        Logger.i(JSON.toJSONString(map));
        return map;
    }


    @GetMapping(path = "/userInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserInfo userInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("123");
        userInfo.setUserName("AndServer");
        return userInfo;
    }

}