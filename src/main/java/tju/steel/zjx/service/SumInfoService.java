package tju.steel.zjx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tju.steel.zjx.entity.SumInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import tju.steel.zjx.entity.vo.SumInfoQueryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
/**
 * 类功能：型钢信息的额外查询方式
 */
public interface SumInfoService extends IService<SumInfo> {

    /**
     * 根据型钢编号查询型钢
     * @param number    型钢编号
     * @return          型钢信息
     */
    SumInfo getByNumber(String number);

    /**
     * 分页查询所有符合前端筛选条件的型钢信息
     * @param pageParam         分页条件
     * @param sumInfoQueryVo    查询条件
     * @return                  型钢信息
     */
    IPage<SumInfo> selectPage(Page<SumInfo> pageParam, SumInfoQueryVo sumInfoQueryVo);

    /**
     * 根据型钢编号设置已查看
     * @param id    型钢编号
     */
    void changeViewedById(String id);

    /**
     * 根据型钢缺陷数降序排列
     * @param page 页数
     * @param limit 每页记录数
     * @return
     */
    IPage<SumInfo> selectDefectsPage(Long page, Long limit);
}

