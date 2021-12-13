package com.edbootcamp.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.edbootcamp.entity.UserImpl;
import com.edbootcamp.service.UserService;

@RunWith(MockitoJUnitRunner.class)
@Test
public class UserDAOImplTest{

	@Mock
    private UserDao userDao;
	
	@InjectMocks
    private UserService userService;
	
	@Spy
    private List<UserImpl> users = new ArrayList<UserImpl>();
	
	@Captor
    private ArgumentCaptor<UserImpl> captor;
	
	@BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        users = getUserList();
    }

	@AfterMethod
	public void teardown() {
		users.clear();
	}
	
	@Test
    public void saveUser(){
		userService.saveUser(users.get(1));
        verify(userDao, times(1)).saveUser(captor.capture());
        Assert.assertEquals(captor.getValue().getFirstName(), "David");
        Assert.assertEquals(captor.getValue().getLastName(), "Yang");
        Assert.assertEquals(captor.getValue().getUserName(), "david");
        
        Assert.assertEquals(3, users.size());
        verify(users, times(3)).add(any(UserImpl.class));
    }
	
	@Test(expectedExceptions = RuntimeException.class)
    public void saveExistingUsers() {
        doThrow(RuntimeException.class).when(userDao).saveUser((UserImpl) users.get(0));
        userService.saveUser(any(UserImpl.class));
    }
	
	@Test
	public void findAllUsers() {
		//userService.getAllUsers();
        when(userDao.getAllUsers()).thenReturn(users);
        Assert.assertEquals(userService.getAllUsers(), users);
        verify(userDao, times(1)).getAllUsers();
    }
	@Test
	public void deleteUser() {
		when(userDao.findById(11L)).thenReturn(null);
        userService.deleteUser(users.get(2));
        verify(userDao, times(1)).deleteUser(users.get(2));
        
    }
	
	@Test(expectedExceptions = RuntimeException.class)
    public void deleteUserNotExist() {
        doThrow(RuntimeException.class).when(userDao).deleteUser((UserImpl) users.get(0));
        userService.deleteUser((UserImpl) users.get(0));
        verify(userDao, atLeastOnce()).deleteUser((UserImpl) users.get(0));
    }
	

	public List<UserImpl> getUserList(){
		UserImpl user1 = new UserImpl();
		user1.setId(3L);
		user1.setFirstName("Jose");
		user1.setLastName("Cruz");
		user1.setUserName("MrRecipe");
		user1.setEmail("test@test.com");
		user1.setPassword("123456");
		
		UserImpl user2 = new UserImpl();
		user2.setId(12L);
		user2.setFirstName("David");
		user2.setLastName("Yang");
		user2.setUserName("david");
		user2.setEmail("tester@tester.com");
		user2.setPassword("123");
		
		UserImpl user3 = new UserImpl();
		user3.setId(11L);
		user3.setFirstName("Connor");
		user3.setLastName("Schultz");
		user3.setUserName("connor");
		user3.setEmail("testing@testing.com");
		user3.setPassword("123654");
		
		users.add(user1);
		users.add(user2);
		users.add(user3);

		return users;
	}
	
}
