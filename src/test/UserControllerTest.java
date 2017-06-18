package test;

import junit.framework.TestCase;

import model.User;

import org.junit.Test;

import controller.UserController;

/**
 * RepositoryController.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class UserControllerTest extends TestCase {
  UserController uc = new UserController("audrynyonata"); 
  User u = uc.getUserInfo();
  
  @Test
  public void testGetter(){
    assertEquals("audrynyonata", uc.getUsername());
  }
 
  @Test
  public void testGetUserInfo1(){
    assertEquals("audrynyonata", u.getUsername());
  }

  @Test
  public void testGetUserInfo2(){
    assertNotNull(u.getEmail());
  }
  
  @Test
  public void testGetUserInfo3(){
    assertNotNull(u.getNamaPengguna());
  }
  
  @Test
  public void testGetUserInfo4(){
    assertFalse(u.getNRepo() < 0);
  }
  
  @Test
  public void testGetUserInfo5(){
    assertFalse(u.getNFollower() < 0);
  }
}
