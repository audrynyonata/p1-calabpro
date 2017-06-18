package test;

import junit.framework.TestCase;

import model.User;

import org.junit.Test;

import controller.ParserJson;

/**
 * ParserJsonTest.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class ParserJsonTest extends TestCase{
  ParserJson x = new ParserJson();
  
  @Test
  public void testGithubUrl(){
    assertEquals("https://api.github.com",x.getGithubUrl());
  }
  
  @Test
  public void testGetter(){
    assertEquals("",x.getString());
  }
  
  @Test
  public void testGetFromUrl(){
    String s = x.getFromUrl(x.getGithubUrl() + "/zen");
    assertNotNull(s);
  }
  
  @Test
  public void testGet1(){
    ParserJson y = new ParserJson("{\"login\":\"audrynyonata\",\"name\":\"Audry Nyonata\",\"site_admin\":false}");
    assertEquals("audrynyonata", y.get(ParserJson.USERNAME, 0));
  }

  @Test
  public void testGet2(){
    ParserJson y = new ParserJson("{\"login\":\"audrynyonata\",\"name\":\"Audry Nyonata\",\"site_admin\":false}");
    assertEquals("Audry Nyonata", y.get(ParserJson.NAMA_PENGGUNA, 24));
  }

  
  @Test
  public void testSetSearch1(){
    x.setAsJsonSearch("tom", "fullname", 10, 5);
    Integer count = Integer.parseInt(x.get(ParserJson.TOTAL_COUNT, 0));
    assertNotNull(count);
  }
  
  public void testSetSearch2(){
    x.setAsJsonSearch("tom", "fullname", 10, 5);
    Integer count = Integer.parseInt(x.get(ParserJson.TOTAL_COUNT, 0));
    if (count > 0){
      assertFalse(!x.getString().contains("tom"));
    } else {
      assertNotNull(x.getString());
    }
  }

  @Test
  public void testSetUser(){
    x.setAsJsonUser("audrynyonata");
    assertFalse(!x.get(ParserJson.USERNAME,0).equals("audrynyonata"));
  }

  @Test
  public void testSetRepo(){
    User u = new User("audrynyonata", "audrynyonata@gmail.com", "Audry Nyonata", 3, 0);
    x.setAsJsonRepo(u);
    
  }
  

}
