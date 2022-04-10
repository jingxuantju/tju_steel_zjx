package tju.steel.zjx.controller;


import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tju.steel.zjx.entity.Member;
import tju.steel.zjx.entity.vo.LoginVo;
import tju.steel.zjx.service.MemberService;
import tju.steel.zjx.utils.R;

import java.util.List;

@Api("用户信息查询")
@CrossOrigin    // 注意跨域
@RestController
@RequestMapping("/admin/steel/img")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("导出用户分页数据至excel表")
    @GetMapping("/listPageExportExcel")
    public R listPageExportExcel(){

        List<Member> records = memberService.list();
        String fileName = "D:\\java\\shizhan\\" + "用户表.xlsx";
        EasyExcel.write(fileName, Member.class)
                .sheet("用户信息")
                .doWrite(records);
        return R.ok().message("导出成功");
    }

}
