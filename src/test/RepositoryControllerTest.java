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
  /**
   * Atribut testcase bertipe user.
   */
  User user;
  /**
   * Atribut testcase bertipe repositoryController.
   */
  RepositoryController rc;
  /**
   * Atribut testcase bertipe array of repository.
   */
  Repository[] arr;
  /**
   * Atribut int berisi jumlah repository dari user.
   */
  int count;
  
  /**
   * Menginisialisasi testcase.
   */
  @Before
  public void setUp() {
    user = new User("audrynyonata", "audrynyonata@gmail.com", "Audry Nyonata", 3, 0);
    rc = new RepositoryController(user);
    arr = rc.getRepos();
    count = rc.getOwner().getNRepo();
  }

  /**
   * Memanggil getOwner dari repositoryController.
   */
  @Test
  public void testGetter() {
    assertEquals(user.getUsername(),rc.getOwner().getUsername());
  }

  /**
   * Memanggil getRepos, memastikan jumlah repository benar.
   */
  @Test
  public void testGetRepos1() {
    assertEquals(arr.length, count);
  }

  /**
   * Memanggil getRepos, memastikan array menyimpan null pada kondisi tepat.
   */
  @Test
  public void testGetRepos2() {
    if (count == 0) {
      assertFalse(!arr.equals(null));
    } else {
      assertNotNull(arr);
    }
  }
  
  /**
   * Memanggil getRepos, memastikan elemen array tidak null.
   */
  @Test
  public void testGetRepos3() {
    if (count != 0) {
      assertNotNull(arr[0]);
    }
  }
  
  /**
   * Memanggil getNama dari repository elemen array.
   */
  @Test
  public void testGetNama() {
    if (count != 0) {
      assertNotNull(arr[0].getNama());
    }
  }
  
  /**
   * Memanggil getDeskripsi dari repository elemen array.
   */
  @Test
  public void testGetDeskripsi() {
    if (count != 0) {
      assertNotNull(arr[0].getDeskripsi());
    }
  }
  
  /**
   * Memanggil getUrl dari repository elemen array.
   */
  @Test
  public void testGetUrl() {
    if (count != 0) {
      assertNotNull(arr[0].getUrl());
    }
  }
}
