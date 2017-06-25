package test;

import controller.UserController;

import junit.framework.TestCase;

import model.User;

import org.junit.Before;
import org.junit.Test;

/**
 * UserControllerTest.java.
 * Melakukan test untuk user dan userController.
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class UserControllerTest extends TestCase {
  /**
   * Atribut testcase bertipe UserController.
   */
  UserController uc;
  /**
   * Atribut testcase bertipe user.
   */
  User user;
  
  /**
   * Menginisialisasi testcase.
   */
  @Before
  public void setUp() {
    uc = new UserController("audrynyonata"); 
    user = uc.getUserInfo();
  }
  
  /**
   * Memanggil getUsername dari usercontroller.
   */
  @Test
  public void testGetter() {
    assertEquals("audrynyonata", uc.getUsername());
  }
 
  /**
   * Memanggil getUsername dari user.
   */
  @Test
  public void testGetUserInfo1() {
    assertEquals("audrynyonata", user.getUsername());
  }

  /**
   * Memanggil getEmail dari user.
   */
  @Test
  public void testGetUserInfo2() {
    assertNotNull(user.getEmail());
  }
  
  /**
   * Memanggil getNamaPengguna dari user.
   */
  @Test
  public void testGetUserInfo3() {
    assertNotNull(user.getNamaPengguna());
  }
  
  /**
   * Memanggil getNRepo dari user.
   */
  @Test
  public void testGetUserInfo4() {
    assertFalse(user.getNRepo() < 0);
  }
  
  /**
   * Memanggil getNFollower dari user.
   */
  @Test
  public void testGetUserInfo5() {
    assertFalse(user.getNFollower() < 0);
  }
}
