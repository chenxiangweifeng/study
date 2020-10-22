package com.example.study.core.controller.servant;

import com.example.study.core.model.vo.servant.PositionVo;
import com.example.study.core.service.servant.ImportService;
import com.example.study.core.service.servant.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pos")
@Api(description = "职位控制类")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/findByCode")
    @ApiOperation(value = "根据职位码查询报考职位")
    public PositionVo insertPositionTable(String positionCode){
        PositionVo vo = positionService.findPositionByPosCode(positionCode);
        return vo;
    }



}
