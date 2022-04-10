package tju.steel.zjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import tju.steel.zjx.entity.SumInfo;
import tju.steel.zjx.entity.vo.SumInfoQueryVo;
import tju.steel.zjx.mapper.SumInfoMapper;
import tju.steel.zjx.service.SumInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
@Service
public class SumInfoServiceImpl extends ServiceImpl<SumInfoMapper, SumInfo> implements SumInfoService {

    /**
     * 根据型钢编号查询型钢
     * @param number    型钢编号
     * @return          型钢信息
     */
    @Override
    public SumInfo getByNumber(String number) {

        //显示查询结果
        QueryWrapper<SumInfo> queryWrapper = new QueryWrapper<>();

        //查询匹配
        queryWrapper.likeRight("number", number);

        //得到查询结果并返回
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 分页查询所有符合前端筛选条件的型钢信息
     * @param pageParam         分页条件
     * @param sumInfoQueryVo    查询条件
     * @return                  型钢信息
     */
    @Override
    public IPage<SumInfo> selectPage(Page<SumInfo> pageParam, SumInfoQueryVo sumInfoQueryVo) {

        //显示分页查询列表
        QueryWrapper<SumInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        //若无查询信息，直接返回分页结果
        if(sumInfoQueryVo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

        //条件查询
        //获取要匹配的信息
        String number = sumInfoQueryVo.getNumber();
        String type = sumInfoQueryVo.getType();
        String joinDateBegin = sumInfoQueryVo.getJoinDateBegin();
        String joinDateEnd = sumInfoQueryVo.getJoinDateEnd();

        //判断信息是否为空，如不为空进行匹配
        //1. 若编号不为空则做相似查询
        if(!StringUtils.isEmpty(number)){
            queryWrapper.eq("number", number);
        }

        //2. 若型号不为空，做型号匹配查询
//        if(type != null){
//            queryWrapper.eq("type", type);
//        }

        if(!StringUtils.isEmpty(type)){
            queryWrapper.eq("type", type);
        }

        //3. 生产日期不为空
        if(!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("join_date", joinDateBegin);
        }

        //4. 截止日期不为空
        if(!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("join_date", joinDateEnd);
        }

        //返回最终结果
        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    /**
     * 根据型钢编号设置已查看
     * @param id    型钢编号
     */
    @Override
    public void changeViewedById(String id) {

        //显示查询结果
        QueryWrapper<SumInfo> queryWrapper = new QueryWrapper<>();

        //查询匹配
        queryWrapper.eq("id", id);

        //得到查询结果
        SumInfo sumInfo = baseMapper.selectOne(queryWrapper);

        //更新 viewed 字段
        sumInfo.setViewed(1);

        //更新结果
        baseMapper.updateById(sumInfo);
    }

    /**
     * 根据型钢缺陷数降序排列
     * @param page 页数
     * @param limit 每页记录数
     * @return
     */
    @Cacheable(value = "index", key = "'selectDefectsPage'")
    @Override
    public IPage<SumInfo> selectDefectsPage(Long page, Long limit) {

        QueryWrapper<SumInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("defects");
        Page<SumInfo> pageParam = new Page<>(page, limit);
        return baseMapper.selectPage(pageParam, queryWrapper);
    }

}
