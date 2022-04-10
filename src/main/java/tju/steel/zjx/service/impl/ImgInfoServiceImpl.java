package tju.steel.zjx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import tju.steel.zjx.entity.ImgInfo;
import tju.steel.zjx.entity.vo.ImgInfoQueryVo;
import tju.steel.zjx.mapper.ImgInfoMapper;
import tju.steel.zjx.service.ImgInfoService;
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
public class ImgInfoServiceImpl extends ServiceImpl<ImgInfoMapper, ImgInfo> implements ImgInfoService {

    /**
     * 查询所有符合前端筛选条件的缺陷图像
     * @param imgInfoQueryVo    查询条件
     * @return                  查询结果
     */


    @Override
    public List<ImgInfo> selectPage(ImgInfoQueryVo imgInfoQueryVo) {

        // 显示分页查询列表
        QueryWrapper<ImgInfo> queryWrapper = new QueryWrapper<>();

        // 条件查询
        // 获取要匹配的信息
        String number = imgInfoQueryVo.getNumber();
        String surface = imgInfoQueryVo.getSurface();
        String type = imgInfoQueryVo.getType();
        double startLength = imgInfoQueryVo.getStartLength();
        double endLength = imgInfoQueryVo.getEndLength();

        //判断信息是否为空，如不为空进行匹配
        //1. 若编号不为空则做相似查询
        if (!StringUtils.isEmpty(number)) {
            queryWrapper.eq("number", number);
        }

        //2. 若型钢面不为空，做型号匹配查询
        if (!StringUtils.isEmpty(surface)) {
            queryWrapper.eq("surface", surface);
        }

        //3. 若缺陷类型不为空，做缺陷匹配查询
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq("type", type);
        }

        //4. 起始缺陷位置不为空
        if (!StringUtils.isEmpty(startLength)) {
            queryWrapper.ge("length", startLength);
        }

        //5. 终止缺陷位置不为空，此处默认型钢最大长度为 200m
        if (endLength == 0.0) {
            queryWrapper.le("length", 200);
        }
        else {
            queryWrapper.le("length", endLength);
        }

        //获取最终结果并返回
        List<ImgInfo> imgInfos = baseMapper.selectList(queryWrapper);

        return imgInfos;
    }

    /**
     * 分页查询所有符合前端筛选条件的缺陷图像
     * @param pageParam         分页
     * @param imgInfoQueryVo    查询条件
     * @return                  查询结果
     */
//    @Cacheable(value = "index", key = "'selectPage'")
    @Override
    public IPage<ImgInfo> selectPage(Page<ImgInfo> pageParam, ImgInfoQueryVo imgInfoQueryVo) {

        //显示分页查询列表
        QueryWrapper<ImgInfo> queryWrapper = new QueryWrapper<>();

        //若无查询信息，直接返回分页结果
        if(imgInfoQueryVo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

        //条件查询
        //获取要匹配的信息
        String number = imgInfoQueryVo.getNumber();
        String surface = imgInfoQueryVo.getSurface();
        String type = imgInfoQueryVo.getType();
        double startLength = imgInfoQueryVo.getStartLength();
        double endLength = imgInfoQueryVo.getEndLength();

        //判断信息是否为空，如不为空进行匹配
        //1. 若编号不为空则做相似查询
        if (!StringUtils.isEmpty(number)) {
            queryWrapper.eq("number", number);
        }

        //2. 若型钢面不为空，做型号匹配查询
        if (!StringUtils.isEmpty(surface)) {
            queryWrapper.eq("surface", surface);
        }

        //3. 若缺陷类型不为空，做缺陷匹配查询
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq("type", type);
        }

        //4. 起始缺陷位置不为空
        if (!StringUtils.isEmpty(startLength)) {
            queryWrapper.ge("length", startLength);
        }

        //5. 终止缺陷位置不为空，此处默认型钢最大长度为 200m
        if (endLength == 0.0) {
            queryWrapper.le("length", 100);
        }
        else {
            queryWrapper.le("length", endLength);
        }

        //获取最终结果并返回
        return baseMapper.selectPage(pageParam, queryWrapper);
    }
}
