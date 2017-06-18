package test;

import junit.framework.TestCase;

import model.Repository;
import model.User;

import org.junit.Test;


import controller.RepositoryController;

/**
 * RepositoryControllerTest.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class RepositoryControllerTest extends TestCase{
  User u = new User("audrynyonata", "audrynyonata@gmail.com", "Audry Nyonata", 3, 0);
  RepositoryController rc = new RepositoryController(u);
  Repository[] arr = rc.getRepos();
  int count = rc.getOwner().getNRepo();
  
  @Test
  public void testGetter(){
    assertEquals(u.getUsername(),rc.getOwner().getUsername());
  }

  @Test
  public void testGetRepos1(){
    assertEquals(arr.length, count);
  }
  
  @Test
  public void testGetRepos2(){
    if (count == 0){
      assertFalse(!arr.equals(null));
    } else {
      assertNotNull(arr);
    }
  }
  
  @Test
  public void testGetRepos3(){
    if (count != 0){
      assertNotNull(arr[0]);
    }
  }
  
  @Test
  public void testGetNama(){
    if (count != 0){
      assertNotNull(arr[0].getNama());
    }
  }
  
  @Test
  public void testGetDeskripsi(){
    if (count != 0){
      assertNotNull(arr[0].getDeskripsi());
    }
  }
  
  @Test
  public void testGetUrl(){
    if (count != 0){
      assertNotNull(arr[0].getUrl());
    }
  }
}
