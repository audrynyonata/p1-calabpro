package test;

import controller.RepositoryController;

import junit.framework.TestCase;

import model.Repository;
import model.User;

import org.junit.Before;
import org.junit.Test;


/**
 * RepositoryControllerTest.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class RepositoryControllerTest extends TestCase {
  User user;
  RepositoryController rc;
  Repository[] arr;
  int count;
  
  @Before
  public void setUp() {
    user = new User("audrynyonata", "audrynyonata@gmail.com", "Audry Nyonata", 3, 0);
    rc = new RepositoryController(user);
    arr = rc.getRepos();
    count = rc.getOwner().getNRepo();
  }
  
  @Test
  public void testGetter() {
    assertEquals(user.getUsername(),rc.getOwner().getUsername());
  }

  @Test
  public void testGetRepos1() {
    assertEquals(arr.length, count);
  }
  
  @Test
  public void testGetRepos2() {
    if (count == 0) {
      assertFalse(!arr.equals(null));
    } else {
      assertNotNull(arr);
    }
  }
  
  @Test
  public void testGetRepos3() {
    if (count != 0) {
      assertNotNull(arr[0]);
    }
  }
  
  @Test
  public void testGetNama() {
    if (count != 0) {
      assertNotNull(arr[0].getNama());
    }
  }
  
  @Test
  public void testGetDeskripsi() {
    if (count != 0) {
      assertNotNull(arr[0].getDeskripsi());
    }
  }
  
  @Test
  public void testGetUrl() {
    if (count != 0) {
      assertNotNull(arr[0].getUrl());
    }
  }
}
