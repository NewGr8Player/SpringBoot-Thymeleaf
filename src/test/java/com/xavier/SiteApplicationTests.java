package com.xavier;

import com.xavier.bean.User;
import com.xavier.common.util.PasswordUtil;
import com.xavier.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    @Rollback(false)
    public void addUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassSalt(BCrypt.gensalt());
        user.setPassword(PasswordUtil.encryptPassword("0"));
        userDao.insert(user);
    }

    @Test
    @Rollback(false)
    public void updateUser() {
        User user = new User();
        user.setId("7d67d3f8b72b43ce9890d46eadccd819");
        user.setUsername("xavier");
        user.setPassSalt(BCrypt.gensalt());
        user.setPassword(PasswordUtil.encryptPassword("0"));
        userDao.updateById(user);
    }

    @Test
    public void loginTest() {
        User user = userDao.findByUsername("admin");
        Assert.assertTrue(PasswordUtil.passwordValidator("0", user.getPassSalt()));
    }
}
