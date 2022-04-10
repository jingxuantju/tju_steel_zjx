package tju.steel.zjx.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tju.steel.zjx.entity.Member;

import tju.steel.zjx.entity.vo.LoginVo;
import tju.steel.zjx.mapper.MemberMapper;
import tju.steel.zjx.service.MemberService;



@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Cacheable(value = "index", key = "'login'")
    public Member login(LoginVo loginVo) {

        String nickname = loginVo.getNickname();
        String password = loginVo.getPassword();
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname", nickname);
        Member member = memberMapper.selectOne(queryWrapper);
        if(member == null) return null;
        if(member.getPassword().equals(password)){
            return member;
        }
        return null;
    }
}