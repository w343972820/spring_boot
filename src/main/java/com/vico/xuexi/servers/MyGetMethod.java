package com.vico.xuexi.servers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这方法可以获取到Cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie=new Cookie("login","vico");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }
    /*
    * 要求客户端携带cookie访问
    * */
    @RequestMapping(value = "/get/withc/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookie访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "必需携带cookies信息来";
        }
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("vico")){
                return "带cookie了...访问成功...";
            }
        }
        return "必需携带cookies信息来";
    }
    /*
    * 开发一个需要携带参数的get请求
    * 第一种:url:key=value&key=value
    * 模拟获取商品列表
    * */
    @RequestMapping(value = "/getWithParam",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的请求方法一",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋子",400);
        myList.put("面条",2);
        myList.put("衣服",250);
        return myList;
    }
    /*
    * 第二种需要携带参数访问的get请求
    * url:ip:port/getWithParam/10/20
    * */
    @RequestMapping(value = "/getWithParam/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问的请求方法二",httpMethod = "GET")
    public Map<String,Integer> myGetList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("鞋子",400);
        myList.put("面条",2);
        myList.put("衣服",250);
        return myList;
    }



}
