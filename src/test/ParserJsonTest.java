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
  ParserJson tc;
  
  @Before
  public void setUp() {
    tc = new ParserJson();
  }
  
  @Test
  public void testGithubUrl() {
    assertEquals("https://api.github.com",tc.getGithubUrl());
  }
  
  @Test
  public void testGetter() {
    assertEquals("",tc.getString());
  }
  
  @Test
  public void testGetFromUrl() {
    String s = tc.getFromUrl(tc.getGithubUrl() + "/zen");
    assertNotNull(s);
  }
  
  @Test
  public void testGet1() {
    ParserJson y = new ParserJson(
        "{\"login\":\"audrynyonata\",\"name\":\"Audry Nyonata\",\"site_admin\":false}");
    assertEquals("audrynyonata", y.get(ParserJson.USERNAME, 0));
  }

  @Test
  public void testGet2() {
    ParserJson y = new ParserJson(
        "{\"login\":\"audrynyonata\",\"name\":\"Audry Nyonata\",\"site_admin\":false}");
    assertEquals("Audry Nyonata", y.get(ParserJson.NAMA_PENGGUNA, 24));
  }
  
  @Test
  public void testSetSearch1() {
    tc.setAsJsonSearch("tom", "fullname", 10, 5);
    Integer count = Integer.parseInt(tc.get(ParserJson.TOTAL_COUNT, 0));
    assertNotNull(count);
  }

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

  @Test
  public void testSetUser() {
    tc.setAsJsonUser("audrynyonata");
    assertFalse(!tc.get(ParserJson.USERNAME,0).equals("audrynyonata"));
  }

  @Test
  public void testSetRepo() {
    User u = new User("audrynyonata", "audrynyonata@gmail.com", "Audry Nyonata", 3, 0);
    tc.setAsJsonRepo(u);
    
  }
  

}
