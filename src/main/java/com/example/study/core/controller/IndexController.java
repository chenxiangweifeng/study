package com.example.study.core.controller;

import com.example.study.common.domain.LayuiResult;
import com.example.study.common.utils.LayuiUtil;
import com.example.study.core.model.entity.Person;
import com.example.study.core.model.vo.DeploymentVo;
import com.example.study.core.model.vo.FormVO;
import com.example.study.core.model.vo.servant.StudentVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 沉香微风
 * 都是重定向跳转使用等
 */

@Controller
@RequestMapping(value = "/th")
@Api(description = "测试带有前端的thymyleaf")
public class IndexController {


    @ApiOperation(value = "得到deployment")
    @RequestMapping(value = "/deploy", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<DeploymentVo> getDeployment() {
        List list = new ArrayList<DeploymentVo>();
        for (int i=0;i<30;i++){
            DeploymentVo vo = new DeploymentVo();
            vo.setId("11"+i);
            vo.setName("部署文件"+i);
            list.add(vo);
        }
        return list;
    }

    @ApiOperation(value = "接受测试2")
    @RequestMapping(value = "/accept2",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @CrossOrigin
    public String accept2(FormVO vo) {
        System.out.println("APPLICATION_FORM_URLENCODED_VALUE接受到的参数是："+vo+vo.getThing());
        return "添加成功！";
    }

    @ApiOperation(value = "得到deployment")
    @RequestMapping(value = "/accept",
            method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public String accept(@RequestBody FormVO vo) {
        System.out.println("接受到的参数是："+vo+vo.getThing());
        return "success啦啦啦";
    }

    @ApiOperation(value = "跳转进入portal页面")
    @RequestMapping(value = "/portal", method = RequestMethod.GET)
    public String portal() {
        return "portal";
    }

    @ApiOperation(value = "index页面")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("uid","123456789");
        model.addAttribute("name","Jerry");
        return "index.html";
    }

    @ApiOperation(value = "list数据遍历")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listDataPersons(Model model) {
        List<String> languages = Lists.newArrayList("java","c++","python","js");
        model.addAttribute("languages",languages);
        return "index.html";
    }

    @ApiOperation(value = "一个复合对象")
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String multiPerson(Model model) {
        Person p = new Person();
        p.setId("001");
        p.setAge(18);
        p.setUserName("alice");
        p.setAddr("china-beijing");
        p.setCreateTime(new Date());
        model.addAttribute("p",p);
        return "index.html";
    }

    @ApiOperation(value = "跳转对象使用")
    @CrossOrigin
    @RequestMapping(value = "/fps", method = RequestMethod.GET)
    public String lispsPersons() {
        return "persons";
    }

    @ApiOperation(value = "跳转对象使用")
    @CrossOrigin
    @RequestMapping(value = "/fadd", method = RequestMethod.GET)
    public String fAddPerson(Model model) {
        model.addAttribute("p",new Person());
        return "addp";
    }


    @ApiOperation(value = "跳转对象使用")
    @CrossOrigin
    @RequestMapping(value = "/addStu", method = RequestMethod.GET)
    public String fAddStu(Model model) {
        model.addAttribute("p",new StudentVo());
        return "/servant/addStudent";
    }


    @ApiOperation(value = "多个一个复合对象")
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/ps", method = RequestMethod.GET)
    public LayuiResult<Person> multiPersons(Model model,@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit) {
        System.out.println("page="+page+"limit==="+limit);
        Person p1 = new Person();
        p1.setId("001");
        p1.setAge(18);
        p1.setUserName("alice");
        p1.setAddr("china-beijing");
        p1.setCreateTime(new Date());
        Person p2 = new Person();
        p2.setId("002");
        p2.setAge(26);
        p2.setUserName("bob");
        p2.setAddr("china-hangzhou");
        p2.setCreateTime(new Date());
        Person p3 = new Person();
        p1.setId("003");
        p3.setAge(18);
        p3.setUserName("alice");
        p3.setAddr("china-beijing");
        p3.setCreateTime(new Date());
        Person p4 = new Person();
        p4.setId("002");
        p4.setAge(26);
        p4.setUserName("bob");
        p4.setAddr("china-hangzhou");
        p4.setCreateTime(new Date());
        List<Person> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        ps.add(p4);

        for(int i= 0;i<100;i++){
            Person p = new Person();
            p.setId("00"+i);
            p.setAge(i);
            p.setUserName("rose"+i);
            p.setAddr("beijing"+i);
            p.setCreateTime(new Date());
            ps.add(p);
        }
        model.addAttribute("ps",ps);
        LayuiResult<Person> result =   LayuiUtil.buildLayuiPageResult(null);
        return result;
    }

    @ApiOperation(value = "跳转表单")
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String redirectForm(Model model) {
        model.addAttribute("inper",new Person());
        return "addp";
    }
    @ApiOperation(value = "保存表单")
    @RequestMapping(value = "/saveform", method = RequestMethod.POST)
    public String saveform(@ModelAttribute Person inper) {
        System.out.println("整个实体对象是："+inper+"一个属性是："+inper.getUserName());


        return "ok";
    }






}
