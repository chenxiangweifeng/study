package com.example.study.core.controller.servant;

import com.example.study.core.service.servant.ImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;


@RestController
@RequestMapping(value = "/import")
@Api(description = "数据导入类")
public class ImportDataController {

    @Autowired
    private ImportService importService;

    @GetMapping("/insertPosition")
    @ApiOperation(value = "插入考生职位")
    public String insertPositionTable(){
        importService.importPositionTable();
        return "import position successfully!";
    }

    /**
     * 文件上传示例
     * @return
     */
    @PostMapping("/uploadFile")
    @ApiOperation(value = "上传文件")
    public String upload(MultipartFile file) throws Exception{
        System.out.println("文件名称是什么："+file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        String content = new String(bytes);
        System.out.println(content);
        return "upload successfully!";
    }





}
