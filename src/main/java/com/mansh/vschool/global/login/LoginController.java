package com.mansh.vschool.global.login;

import com.mansh.vschool.custom.ParamConst;
import com.mansh.vschool.custom.ResultData;
import com.mansh.vschool.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/")
@Api(tags = "登录接口")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "账户ID" , dataTypeClass = String.class , example = "root" , required = true),
            @ApiImplicitParam(name = "password",value = "密码" , dataTypeClass = String.class , example = "123456" , required = true)
    })
    @PostMapping("/login")
    public ResultData login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="userName",defaultValue = "1") String userName, @RequestParam(value="password",defaultValue = "1") String password, @RequestParam(value="code",defaultValue = "1") String code, @RequestParam(value="curTime",defaultValue = "1") String curTime){
        return loginService.login(request,response,userName,password,code,curTime);
    }

    @ApiOperation("获取验证码图片接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "curTime",value = "当前时间",required = true),
    })
    @GetMapping("/authCodeImage/{curTime}")
    public void authCodeImage(HttpServletResponse response,@PathVariable("curTime") String curTime) throws IOException {
        BufferedImage buffer = new BufferedImage(170,40, BufferedImage.TYPE_INT_RGB);
        String authCode = CommonUtils.drawImage(buffer,170,40);
        redisTemplate.opsForValue().set(curTime,authCode, Duration.ofMinutes(ParamConst.AUTH_CODE_LAST_TIME_OF_MINUTES));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffer,"jpeg",sos);
        sos.flush();
        sos.close();

    }

}
