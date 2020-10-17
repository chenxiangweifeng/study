package com.example.study.core.controller.servant;

import com.example.study.common.domain.LayuiResult;
import com.example.study.common.utils.LayuiUtil;
import com.example.study.common.utils.MapperUtil;
import com.example.study.core.model.entity.Person;
import com.example.study.core.model.entity.servant.StudentEntity;
import com.example.study.core.model.vo.FormVO;
import com.example.study.core.model.vo.servant.StudentVo;
import com.example.study.core.service.servant.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/stu")
@Api(description = "考生的类")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 保存和更新走一个接口即可
     * @param vo
     * @return
     */
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

    @ApiOperation(value = "testMapper")
    @RequestMapping(value = "/testMapper",
            method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public String testMapper() {
        StudentVo studentVo = new StudentVo();
        studentVo.setAge(18);
        studentVo.setRealName("hello");

        StudentEntity st = MapperUtil.mapperObject(StudentEntity.class, studentVo);
        System.out.println("entity::::::"+st.getRealName());
        return "测试完成！";
    }

    @ApiOperation(value = "分页查询学生")
    @RequestMapping(value = "/queryPageStudent", method = RequestMethod.GET)
    public LayuiResult<StudentVo> queryPageStudent(@RequestParam int page, @RequestParam int limit) {
        Page<StudentVo> studentVoPage = studentService.queryPageStudentsNoArgs(page, limit);
        LayuiResult<StudentVo> studentVoLayuiResult = LayuiUtil.buildLayuiPageResult(studentVoPage);
        return studentVoLayuiResult;
    }

    @ApiOperation(value = "根据id查询学生信息")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public StudentVo findStudentById(@RequestParam String id) {
        StudentVo vo = studentService.findStudentById(id);
        return vo;
    }

    @ApiOperation(value = "根据id删除学生信息")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.POST)
    public void deleteStudentById(@PathVariable String id) {
       studentService.deleteStudentById(id);
    }

}
