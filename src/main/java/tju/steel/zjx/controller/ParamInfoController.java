package tju.steel.zjx.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import tju.steel.zjx.entity.ParamInfo;
import tju.steel.zjx.service.ParamInfoService;
import tju.steel.zjx.utils.R;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
@Api("参数信息传递")
@CrossOrigin
@RestController
@RequestMapping("/admin/steel/param")
public class ParamInfoController {

    @Autowired
    private ParamInfoService paramInfoService;

    /**
     * 型钢参数获取
     * @param paramInfo 型钢参数信息
     * @return
     */
    @ApiOperation("型钢参数获取")
    @GetMapping("getParameter")
    public R listPage(ParamInfo paramInfo){

        // 获取型钢参数信息
        String specification = paramInfo.getSpecification();
        String length = paramInfo.getLength();

//        System.out.println("型钢规格为：" + specification);
//        System.out.println("型钢长度为：" + length);

        // 调用 Java Socket 向 Python 端传递信息
        paramInfoService.transformSpecificationInfo(specification);
        paramInfoService.transformLengthInfo(length);
        // 向 C++ 端传送规格信息
        paramInfoService.transformSpecificationToCPP(specification);

        return R.ok().data("specification", specification).data("length", length);
    }
}

