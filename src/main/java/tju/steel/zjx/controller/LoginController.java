package tju.steel.zjx.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import tju.steel.zjx.entity.Member;
import tju.steel.zjx.entity.vo.ImgInfoQueryVo;
import tju.steel.zjx.entity.vo.LoginVo;
import tju.steel.zjx.service.MemberService;
import tju.steel.zjx.utils.R;

/**
 * 登录功能
 */
@Api("登录")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MemberService memberService;


//    /**
//     * 登录
//     * @return
//     */
//    @PostMapping("/login")
//    public R login(){
//        return R.ok().data("token", "admin");
//    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public R info(){
        return R.ok()
                .data("name", "admin")
                .data("roles", "[admin]")
                .data("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public R logout(){
        return R.ok();
    }


    @PostMapping("/save-test")
    public R saveInfo(@RequestBody ImgInfoQueryVo imgInfoQueryVo){
        redisTemplate.opsForValue().set("info", imgInfoQueryVo);
        return R.ok();
    }

    @GetMapping("/get-test/{key}")
    public R getInfo(@PathVariable String key){
        ImgInfoQueryVo imgInfoQueryVo = (ImgInfoQueryVo)redisTemplate.opsForValue().get(key);
        return R.ok().data("imgInfoQueryVo", imgInfoQueryVo);
    }

    @DeleteMapping("delete-test/{key}")
    public R removeInfo(@PathVariable String key){
        Boolean delete = redisTemplate.delete(key);
        return R.ok();
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        Member member = memberService.login(loginVo);
        if(null != member){
            return R.ok().message("登录成功！");
        }
        else{
            return R.error().message("账户名或密码错误");
        }
    }
}
