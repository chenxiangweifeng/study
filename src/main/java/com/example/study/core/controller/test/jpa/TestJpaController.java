package com.example.study.core.controller.test.jpa;
import com.example.study.common.domain.LayuiResult;
import com.example.study.common.utils.LayuiUtil;
import com.example.study.core.model.entity.Person;
import com.example.study.core.service.jpa.JpaPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author 沉香微风
 * 以后尽量设计标准的rest风格的接口
 * restController 是一个组合注解，组合了@controller 和@ResponseBody 这两个注解
 * http 常用的四种方法：get(查) post（改） put（增） delete（删除） 分别对应
 * restful接口风格的关键操作的是数据，分别使用put增 post 改 get 查 delete 删除   /person 斜杠后面是一个实体数据
 * /person/id 根据id查询一个用户
 * 以后增删改查要把这4种主流的请求方式都用起来
 */

@RestController
@RequestMapping(value = "/jpa")
@Api(description = "JPA测试类-person实体")
public class TestJpaController {

    @Autowired
    private JpaPersonService personService;

    @ApiOperation(value = "批量插入大量数据")
    @RequestMapping(value = "/savePersons", method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public String savePersons(@RequestParam Integer n) {
        personService.batchSavePerson(n);
        return "batch save success!";
    }

    @ApiOperation(value = "根据id查询人员")
    @CrossOrigin
    @RequestMapping(value = "/findByPersonById", method = RequestMethod.GET)
    public Person findByPersonById(@RequestParam String id) {
        return personService.findByPersonById(id);
    }

    @ApiOperation(value = "从前端保存一个用户")
    @RequestMapping(value = "/addp", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person p) {
        personService.save(p);
        return "ok";
    }

    @ApiOperation(value = "分页查询人员")
    @RequestMapping(value = "/queryPagePersons", method = RequestMethod.GET)
    public  LayuiResult<Person> queryPagePersons(@RequestParam int page, @RequestParam int limit) {
        Page<Person> pagePeople = personService.queryPagePersons(page, limit);
        LayuiResult<Person> personLayuiResult = LayuiUtil.buildLayuiPageResult(pagePeople);
        return personLayuiResult;
    }

}
