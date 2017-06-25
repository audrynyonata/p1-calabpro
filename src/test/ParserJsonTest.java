package test;

import controller.ParserJson;

import junit.framework.TestCase;

import model.User;

import org.junit.Before;
import org.junit.Test;

/**
 * ParserJsonTest.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class ParserJsonTest extends TestCase {
  /**
   * Atribut testcase bertipe ParserJson.
   */
  ParserJson tc;
  
  /**
   * Menginisialisasi testcase.
   */
  @Before
  public void setUp() {
    tc = new ParserJson();
  }
  
  /**
   * Memanggil getGithubUrl dari parserJson.
   */
  @Test
  public void testGithubUrl() {
    assertEquals("https://api.github.com",tc.getGithubUrl());
  }
  
  /**
   * Memanggil getString dari parserJson.
   */
  @Test
  public void testGetter() {
    assertEquals("",tc.getString());
  }
  
  /**
   * Memanggil getFromUrl.
   * Melakukan koneksi internet, memastikan hasil tidak null.
   */
  @Test
  public void testGetFromUrl() {
    String s = tc.getFromUrl(tc.getGithubUrl() + "/zen");
    assertNotNull(s);
  }
  
  /**
   * Memanggil get username dari indeks nol.
   */
  @Test
  public void testGet1() {
    ParserJson y = new ParserJson(
        "{\"login\":\"audrynyonata\",\"name\":\"Audry Nyonata\",\"site_admin\":false}");
    assertEquals("audrynyonata", y.get(ParserJson.USERNAME, 0));
  }

  /**
   * Memanggil get nama pengguna dari indeks 24.
   */
  @Test
  public void testGet2() {
    ParserJson y = new ParserJson(
        "{\"login\":\"audrynyonata\",\"name\":\"Audry Nyonata\",\"site_admin\":false}");
    assertEquals("Audry Nyonata", y.get(ParserJson.NAMA_PENGGUNA, 24));
  }
  
  /**
   * Memanggil get total count dari indeks nol.
   */
  @Test
  public void testSetSearch1() {
    tc.setAsJsonSearch("tom", "fullname", 10, 5);
    Integer count = Integer.parseInt(tc.get(ParserJson.TOTAL_COUNT, 0));
    assertNotNull(count);
  }

  /**
   * Memanggil setAsJsonSearch.
   */
  @Test
  public void testSetSearch2() {
    tc.setAsJsonSearch("tom", "fullname", 10, 5);
    Integer count = Integer.parseInt(tc.get(ParserJson.TOTAL_COUNT, 0));
    if (count > 0) {
      assertFalse(!tc.getString().contains("tom"));
    } else {
      assertNotNull(tc.getString());
    }
  }

  /**
   * Memanggil setAsJsonUser.
   */
  @Test
  public void testSetUser() {
    tc.setAsJsonUser("audrynyonata");
    assertFalse(!tc.get(ParserJson.USERNAME,0).equals("audrynyonata"));
  }

  /**
   * Memanggil setAsJsonRepo.
   */
  @Test
  public void testSetRepo() {
    User u = new User("audrynyonata", "audrynyonata@gmail.com", "Audry Nyonata", 3, 0);
    tc.setAsJsonRepo(u);
    assertNotNull(tc.get(ParserJson.NAMA_REPOSITORY,0));
  }
}
