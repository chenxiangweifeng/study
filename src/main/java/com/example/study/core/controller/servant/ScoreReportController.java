package com.example.study.core.controller.servant;

import com.example.study.common.utils.MathUtil;
import com.example.study.core.model.vo.servant.ScoreReportVo;
import com.example.study.core.service.servant.ScoreReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(value = "/score")
@Api(description = "考生成绩单控制类")
public class ScoreReportController {

    @Autowired
    private ScoreReportService scoreReportService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    @ApiOperation(value = "模拟插入大量的考生分数信息")
    public String mockBatchSaveScoreReport(@RequestParam int n){
        String[] positionList = {"10001","10002","10003","10004","10005","10006","10007","10008","10009","10010"};
        Random random = new Random();
        for (int i=0; i<n; i++){
            ScoreReportVo vo = new ScoreReportVo();
            vo.setPhoneNum(MathUtil.generateRandomPhoneNum());
            vo.setScore(MathUtil.generateRandomDouble(100));
            vo.setPositionCode(positionList[random.nextInt(positionList.length)]);
            scoreReportService.saveEntity(vo);
        }
        return "ok";
    }

    @GetMapping("/rank")
    @ApiOperation(value = "特定职位的考生排名")
    public ScoreReportVo scoreRankReport(@RequestParam String phoneNum,@RequestParam String positionCode){
        ScoreReportVo vo = scoreReportService.scoreRank(phoneNum, positionCode);
        return vo;
    }

    @GetMapping("/rankAll")
    @ApiOperation(value = "查询该考生总的排名信息")
    public ScoreReportVo scoreRankAllReport(@RequestParam String phoneNum){
        ScoreReportVo vo = scoreReportService.rankAllStudents(phoneNum);
        return vo;
    }


//    /**
//     * 保存和更新走一个接口即可
//     * @param vo
//     * @return
//     */
//    @ApiOperation(value = "保存一个考生的所有信息")
//    @RequestMapping(value = "/save",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ResponseBody
//    @CrossOrigin
//    public String saveStudent(StudentVo vo) {
//        System.out.println("考生的添加信息是："+vo);
//        studentService.save(vo);
//        return "添加考生信息成功！";
//    }


}
