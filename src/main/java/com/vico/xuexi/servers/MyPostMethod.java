package com.vico.xuexi.servers;

import com.vico.xuexi.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post方法")
@RequestMapping("/post")
public class MyPostMethod {
    //这个变量用来装我们cookies信息
    private static Cookie cookie;
    //用户登录成功获取到cookie,然后在访问其它接口列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口,成功后获取cookie信息",httpMethod = "POST")
    public String login(HttpServletResponse response, @RequestParam(value = "username",required = true) String userName,
                        @RequestParam(value = "password",required = true) String passWd){
        if (userName.equals("zhangsan") && passWd.equals("123456")){
            cookie = new Cookie("login","vico");
            response.addCookie(cookie);
            return "恭喜登录成功了..";
        }
        return "用户名或密码错误..";
    }
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User u){
        //需要header传cookie,在传入json的User数据如:{"age": "lala","name": "fdsaf","password": "123456","sex": "string","username": "zhangsan"}
        User user;
        Cookie[] cookies = request.getCookies();
        for(Cookie cook:cookies){
            if (cook.getName().equals("login") && cook.getValue().equals("vico")
                && u.getUsername().equals("zhangsan") && u.getPassword().equals("123456")
            ){
                //如果cookie验证通过,在看需不需要验证用户信息
                user = new User();
                user.setName("lisi");
                user.setAge("20");
                user.setSex("man");
                user.setUsername("zhangsansansan");
                user.setPassword("456789");
                return user.toString();
            }
        }
        return "参数不合法...";
    }
}
