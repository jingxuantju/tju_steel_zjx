package tju.steel.zjx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import tju.steel.zjx.entity.ImgInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import tju.steel.zjx.entity.vo.ImgInfoQueryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zjx
 * @since 2021-06-25
 */
public interface ImgInfoService extends IService<ImgInfo> {

    /**
     * 查询所有符合前端筛选条件的缺陷图像
     * @param imgInfoQueryVo    查询条件
     * @return                  查询结果
     */
    List<ImgInfo> selectPage(ImgInfoQueryVo imgInfoQueryVo);

    /**
     * 分页查询所有符合前端筛选条件的缺陷图像
     * @param pageParam         分页
     * @param imgInfoQueryVo    查询条件
     * @return                  查询结果
     */
    IPage<ImgInfo> selectPage(Page<ImgInfo> pageParam, ImgInfoQueryVo imgInfoQueryVo);
}
