package tju.steel.zjx.service;

import tju.steel.zjx.entity.ParamInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
/**
 * 类功能：传递型钢参数
 */
public interface ParamInfoService extends IService<ParamInfo> {

    /**
     * 向检测端传送型钢规格
     * @param specification 型钢型号
     */
    void transformSpecificationInfo(String specification);

    /**
     * 向检测端传送型钢长度
     * @param length    型钢长度
     */
    void transformLengthInfo(String length);

    /**
     * 向采集端传送型钢型号
     * @param specification
     */
    void transformSpecificationToCPP(String specification);
}
