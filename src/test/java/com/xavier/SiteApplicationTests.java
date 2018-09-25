package com.xavier;

import com.xavier.bean.User;
import com.xavier.common.util.PasswordUtil;
import com.xavier.dao.UserDao;
import com.xavier.service.RolePermissionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteApplicationTests {

    @Autowired
    private UserDao userDao;

    /**
     * 新建用户
     */
    @Test
    @Rollback(false)
    public void addUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(PasswordUtil.encryptPassword("0"));
        userDao.insert(user);
    }

    /**
     * 更新用户信息
     */
    @Test
    @Rollback(false)
    public void updateUser() {
        User user = new User();
        user.setId("7d67d3f8b72b43ce9890d46eadccd819");
        user.setUsername("xavier");
        user.setPassword(PasswordUtil.encryptPassword("0"));
        userDao.updateById(user);
    }

    /**
     * 登陆
     */
    @Test
    public void loginTest() {
        User user = userDao.findByUsername("admin");
        Assert.assertTrue(PasswordUtil.passwordValidator("0", user.getPassword()));
    }


    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 自定义批量查询
     */
    @Test
    public void custombatchQueryTest(){
        rolePermissionService.findByBatchRoleIds(Arrays.asList("1","2")).stream().forEach(
                n -> System.out.println(n)
        );
    }
}
