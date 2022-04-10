package tju.steel.zjx.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import tju.steel.zjx.entity.ImgInfo;
import tju.steel.zjx.entity.SumInfo;
import tju.steel.zjx.entity.vo.ImgInfoQueryVo;
import tju.steel.zjx.service.ImgInfoService;
import tju.steel.zjx.service.SumInfoService;
import tju.steel.zjx.utils.Base64Img;
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
@Api("缺陷图像信息查询")
@CrossOrigin    // 注意跨域
@RestController
@RequestMapping("/admin/steel/img")
@Slf4j
public class ImgInfoController {

    @Autowired
    private ImgInfoService imgInfoService;

    @Autowired
    private SumInfoService sumInfoService;

    /**
     * 显示缺陷图像列表
     * @param imgInfoQueryVo    缺陷图像需要匹配的信息
     * @return                  缺陷图像查询结果
     */
    @ApiOperation("显示缺陷图像列表")
    @GetMapping("list")
    public R listPage(ImgInfoQueryVo imgInfoQueryVo){

        List<ImgInfo> records = imgInfoService.selectPage(imgInfoQueryVo);
        long total = records.size();

        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 缺陷图像分页列表查询
     * @param page      查询第几页
     * @param limit     每页显示条数
     * @return
     */
    @ApiOperation("分页显示缺陷图像列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@PathVariable Long page,
                      @PathVariable Long limit,
                      ImgInfoQueryVo imgInfoQueryVo) {

        Page<ImgInfo> pageParam = new Page<>(page, limit);
        IPage<ImgInfo> pageModel = imgInfoService.selectPage(pageParam, imgInfoQueryVo);
        List<ImgInfo> records = pageModel.getRecords();
        long total = records.size();

        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 缺陷图像分页导出至 excel
     * @param page              查询第几页
     * @param limit             每页显示条数
     * @param imgInfoQueryVo    查询条件
     * @return
     */
    @ApiOperation("缺陷图像分页导出至 excel")
    @GetMapping("listExportExcel/{page}/{limit}")
    public R listPageExportExcel(@PathVariable Long page,
                                 @PathVariable Long limit,
                                 ImgInfoQueryVo imgInfoQueryVo) {

        Page<ImgInfo> pageParam = new Page<>(page, limit);
        IPage<ImgInfo> pageModel = imgInfoService.selectPage(pageParam, imgInfoQueryVo);
        List<ImgInfo> records = pageModel.getRecords();
        // 写入 excel 表格
        String fileName = "D:\\java\\shizhan\\" + "缺陷表.xlsx";
        EasyExcel.write(fileName, ImgInfo.class)
                 .sheet("缺陷图像")
                 .doWrite(records);

        return R.ok().message("导出成功，请前往桌面查看!");
    }

    /**
     * 查询所有缺陷图片信息
     * @return
     */
    @Cacheable(value = "index", key = "'listAll'")
    @ApiOperation("查询所有缺陷图片信息")
    @GetMapping("listAll")
    public R listAll(){
        List<ImgInfo> list = imgInfoService.list();
        return R.ok().data("items", list);
    }

    /**
     * 根据 ID 删除缺陷图像
     * @param id    待删除图像ID
     * @return
     */
    @ApiOperation("根据ID删除缺陷图像")
    @DeleteMapping("remove/{id}")
    public R removeById(@PathVariable String id){

        //根据ID获取缺陷图像所属型钢编号
        String number = imgInfoService.getById(id).getNumber();
        //根据型号编号获取型钢具体信息
        SumInfo sumInfo = sumInfoService.getByNumber(number);
        //更新型钢信息
        sumInfo.setDefects(sumInfo.getDefects() - 1);
        sumInfoService.updateById(sumInfo);

        //删除图像
        boolean result = imgInfoService.removeById(id);

        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    /**
     * 根据ID查询缺陷图像所在位置
     * @param id    待查询图像ID
     * @return
     */
    @ApiOperation("根据ID查询缺陷图像所在位置")
    @GetMapping("location/{id}")
    public R imgLocation(@PathVariable String id){

        //根据ID获取缺陷图像所属型钢编号
        String link = imgInfoService.getById(id).getLink();
        System.out.println(link);
        link = Base64Img.getImgFileToBase64(link);
//        Base64.getEncoder();

        System.out.println("***********************" + link + "*************************");

        return R.ok().data("link", link);
    }
}

