package com.finhacker.smarket.service;

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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagerServiceTest {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
        userRepository.save(new UserManager("abc", new BCryptPasswordEncoder().encode("123"), "abc"));
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void addFavouriteTest() throws Exception {
        UserManager userManager = (UserManager) userRepository.findByName("abc");
        Integer[] arrInt_0 = new Integer[2];
        arrInt_0[0]=10100068;
        arrInt_0[1]=10100141;
        List<Integer> enterprises=Arrays.asList(arrInt_0);
        Assert.assertTrue(userManagerService.addFavourite(userManager,enterprises));
    }

    @Test
    public void removeFavouriteTest() throws Exception {
        UserManager userManager = (UserManager) userRepository.findByName("abc");
        Integer[] arrInt_0 = new Integer[1];
        arrInt_0[0]=10100068;
        List<Integer> enterprises=Arrays.asList(arrInt_0);
        Assert.assertTrue(userManagerService.addFavourite(userManager,enterprises));
        Assert.assertTrue(userManagerService.removeFavourite(userManager,enterprises));
        Assert.assertTrue(userManagerService.addFavourite(userManager,enterprises));
    }

    @Test
    public void getAllFavouriteTest() throws Exception {
        UserManager userManager = (UserManager) userRepository.findByName("abc");
        Integer[] arrInt_0 = new Integer[2];
        arrInt_0[0]=10100068;
        arrInt_0[1]=10100141;
        List<Integer> enterprises=Arrays.asList(arrInt_0);
        Assert.assertTrue(userManagerService.addFavourite(userManager,enterprises));
        Assert.assertEquals(10100068, (int) userManagerService.getAllFavourite(userManager).get(0));
        Assert.assertEquals(10100141, (int) userManagerService.getAllFavourite(userManager).get(1));
    }
}
