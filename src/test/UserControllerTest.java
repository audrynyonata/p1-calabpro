package test;

import controller.UserController;

import junit.framework.TestCase;

import model.User;

import org.junit.Before;
import org.junit.Test;

/**
 * UserControllerTest.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class UserControllerTest extends TestCase {
  UserController uc;
  User user;
  
  @Before
  public void setUp() {
    uc = new UserController("audrynyonata"); 
    user = uc.getUserInfo();
  }
  
  @Test
  public void testGetter() {
    assertEquals("audrynyonata", uc.getUsername());
  }
 
  @Test
  public void testGetUserInfo1() {
    assertEquals("audrynyonata", user.getUsername());
  }

  @Test
  public void testGetUserInfo2() {
    assertNotNull(user.getEmail());
  }
  
  @Test
  public void testGetUserInfo3() {
    assertNotNull(user.getNamaPengguna());
  }
  
  @Test
  public void testGetUserInfo4() {
    assertFalse(user.getNRepo() < 0);
  }
  
  @Test
  public void testGetUserInfo5() {
    assertFalse(user.getNFollower() < 0);
  }
}
