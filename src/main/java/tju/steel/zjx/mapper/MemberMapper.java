package tju.steel.zjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tju.steel.zjx.entity.Member;


@Mapper
@Repository
public interface MemberMapper extends BaseMapper<Member> {

}