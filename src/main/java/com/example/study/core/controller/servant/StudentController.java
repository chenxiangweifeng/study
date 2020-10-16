package com.example.study.core.controller.servant;

import com.example.study.core.model.vo.FormVO;
import com.example.study.core.model.vo.servant.StudentVo;
import com.example.study.core.service.servant.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/stu")
@Api(description = "考生的类")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @ApiOperation(value = "保存一个考生的所有信息")
    @RequestMapping(value = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @CrossOrigin
    public String saveStudent(StudentVo vo) {
        System.out.println("考生的添加信息是："+vo);
        studentService.save(vo);
        return "添加考生信息成功！";
    }



//
//    @ApiOperation(value = "接受测试2")
//    @RequestMapping(value = "/accept2",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ResponseBody
//    @CrossOrigin
//    public String accept2(FormVO vo) {
//        System.out.println("APPLICATION_FORM_URLENCODED_VALUE接受到的参数是："+vo+vo.getThing());
//        return "添加成功！";
//    }







}
