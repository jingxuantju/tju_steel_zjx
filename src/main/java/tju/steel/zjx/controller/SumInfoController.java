package tju.steel.zjx.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import tju.steel.zjx.entity.SumInfo;
import tju.steel.zjx.entity.vo.SumInfoQueryVo;
import tju.steel.zjx.service.SumInfoService;
import tju.steel.zjx.utils.R;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
@Api("型钢信息查询")
@CrossOrigin
@RestController
@RequestMapping("/admin/steel/sum")
public class SumInfoController {

    @Autowired
    private SumInfoService sumInfoService;

    /**
     *查询所有型钢信息
     * @return
     */
    @Cacheable(value = "index", key = "'list'")
    @ApiOperation("查询所有型钢信息")
    @GetMapping("list")
    public R listAll(){
        List<SumInfo> list = sumInfoService.list();
        long total = list.size();
        return R.ok().data("total", total).data("items", list);
    }

    /**
     * 型钢分页列表查询
     * @param page      查询第几页
     * @param limit     每页显示条数
     * @return
     */
    @ApiOperation("型钢分页列表查询")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@PathVariable Long page,
                      @PathVariable Long limit,
                      SumInfoQueryVo sumInfoQueryVo){

        Page<SumInfo> pageParam = new Page<>(page, limit);
        IPage<SumInfo> pageModel = sumInfoService.selectPage(pageParam, sumInfoQueryVo);
        List<SumInfo> records = pageModel.getRecords();
        long total = records.size();
        System.out.println("total = " + total);
        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 导出型钢分页数据至 excel 表
     * @param page              查询第几页
     * @param limit             每页显示条数
     * @param sumInfoQueryVo    查询条件
     * @return
     */
    @ApiOperation("导出型钢分页数据至excel表")
    @GetMapping("listExportExcel/{page}/{limit}")
    public R listPageExportExcel(@PathVariable Long page,
                                 @PathVariable Long limit,
                                 SumInfoQueryVo sumInfoQueryVo) {

        Page<SumInfo> pageExcel = new Page<>(page, limit);
        IPage<SumInfo> pageExcelModel = sumInfoService.selectPage(pageExcel, sumInfoQueryVo);
        List<SumInfo> records = pageExcelModel.getRecords();
        // 写入 excel 表格
        String fileName = "D:\\java\\shizhan\\" + "缺陷表.xlsx";
        EasyExcel.write(fileName, SumInfo.class)
                 .sheet("型钢信息")
                 .doWrite(records);

        return R.ok().message("导出成功，请前往桌面查看!");
    }

    /**
     * 根据ID删除型钢
     * @param id    待删除型钢ID
     * @return
     */
    @DeleteMapping("remove/{id}")
    public R removeById(@PathVariable String id){

        //删除型钢
        boolean result = sumInfoService.removeById(id);

        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    /**
     * 根据型钢编号设置已查看
     * @param id    已查看型钢编号
     * @return
     */
    @PostMapping("changeViewed/{id}")
    public R changeViewed(@PathVariable String id) {

        sumInfoService.changeViewedById(id);

        return R.ok().message("设置已查看成功");
    }

    /**
     * 分页查看缺陷最多的型钢
     * @param page 查询第几页
     * @param limit 每页显示条数
     * @return
     */

    @ApiOperation("缺陷最多的型钢")
    @GetMapping("/topDefectsImg/{page}/{limit}")
    public R topDefectsImg(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                           @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit){

        IPage<SumInfo> pageModel = sumInfoService.selectDefectsPage(page, limit);
        List<SumInfo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("records", records);
    }
}

