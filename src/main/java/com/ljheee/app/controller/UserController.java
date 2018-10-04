package com.ljheee.app.controller;

import com.ljheee.app.dao.UserDAO;
import com.ljheee.app.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lijianhua04 on 2018/7/18.
 */

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class);




    @Autowired(required = false)
    private UserDAO userDAO;

    @GetMapping(value = "/test")
    public String test(){
        return "ok";
    }


    /**
     * http://localhost:8080/my-simple-ssm/user/add?name=abc&passwd=123&appid=95955542783
     * 添加用户
     * @param name
     * @param passwd
     * @param appid
     * @return
     */
    @GetMapping(value = "/add")
    public User save(@RequestParam(value = "name") String name,
                     @RequestParam(value = "passwd",defaultValue = "0") String passwd,
                     @RequestParam(value = "appid",defaultValue = "0") String appid){

        User user = new User(name,passwd,appid);

        //执行插入，返回主键ID
        int userId = userDAO.addUser(user);
        user.setId(userId);

        return user;
    }


    /**
     * http://localhost:8080/my-simple-ssm/user/list
     *
     * @return
     */
    @GetMapping(value = "/list")
    public List<User> getUserList(){
        logger.info("========getUserList==========");
        return userDAO.getUserList();
    }

    @GetMapping(value = "/list/count")
    public int getUserCount(){
        return userDAO.getUserCount();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(@RequestParam("id") Integer id) {
        userDAO.deleteById(id);
    }


    public static void main(String[] args) {

        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new java.util.Date(now));
        System.out.println(format);// 2018-09-07


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(now));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTimeInMillis());//1536249600149  ,2018-09-07 00:00:00的时间戳
    }

}
