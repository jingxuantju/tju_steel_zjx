package tju.steel.zjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tju.steel.zjx.entity.Member;
import tju.steel.zjx.entity.SumInfo;
import tju.steel.zjx.entity.vo.LoginVo;


public interface MemberService extends IService<Member> {
    Member login(LoginVo loginVo);
}
