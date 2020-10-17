package com.example.study.core.controller.servant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 沉香微风
 * 都是重定向跳转使用等
 */

@Controller
@RequestMapping(value = "/ser")
@Api(description = "跳转")
public class ServantForwardController {

    @ApiOperation(value = "跳转进入考生列表页面")
    @RequestMapping(value = "/stulist", method = RequestMethod.GET)
    public String forStuList() {
        return "servant/stulist";
    }

    @ApiOperation(value = "跳转对象使用")
    @CrossOrigin
    @RequestMapping(value = "/fps", method = RequestMethod.GET)
    public String lispsPersons() {
        return "persons";
    }

    @ApiOperation(value = "跳转对象使用")
    @CrossOrigin
    @RequestMapping(value = "/userlist2", method = RequestMethod.GET)
    public String userlist2() {
        return "servant/userlist2";
    }

//    @ApiOperation(value = "跳转对象使用")
//    @CrossOrigin
//    @RequestMapping(value = "/stulist", method = RequestMethod.GET)
//    public String forwartStuList() {
//        return "servant/stulist";
//    }

}