package com.finhacker.smarket.service;

import com.finhacker.smarket.domain.user.User;
import com.finhacker.smarket.domain.user.UserManager;
import com.finhacker.smarket.domain.user.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userRepository.save(new UserManager("Song Anqi", encoder.encode("123"), "Song Anqi"));
        userRepository.save(new UserManager("Ku Wai Han", encoder.encode("123456"), "Ku Wai Han"));
        userRepository.save(new UserManager("abc", encoder.encode("123"), "abc"));
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    private PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void loadUserByUsernameTest() throws Exception{
        User user_1=userRepository.findByName("Song Anqi");
        Assert.assertEquals(userService.getUserByName("Song Anqi"),user_1);
        Assert.assertNull(userService.getUserByName("username that does not exist"));
    }

    @Test
    public void changePasswordTest() throws Exception{
        User user_2=userRepository.findByName("Ku Wai Han");
        Assert.assertTrue(userService.changePassword(user_2,"123456"));
    }

    @Test
    public void authPasswordTest() throws Exception{
        User user_2=userRepository.findByName("Ku Wai Han");
        Assert.assertTrue(userService.authPassword(user_2,"123456"));
    }

    @Test
    @WithUserDetails(value = "abc", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void getCurrentUserTest() throws Exception{
        Assert.assertNotNull(userService.getCurrentUser());
    }

    @Test
    public void registerManagerTest() throws Exception{
        System.out.println(userService.registerManager("newtest","testpassword","testmanager"));
    }

}
