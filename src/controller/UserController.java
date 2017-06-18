package controller;

import model.User;

/**
 * UserController.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class UserController {
  private String username;
  
  public UserController(){
    username = "";
  }
  
  public UserController(String u){
    username = u;
  }
  
  public String getUsername(){
    return username;
  }
  
  public User getUserInfo(){
    ParserJson x = new ParserJson();
    x.setAsJsonUser(username);
    String username = "";
    String namaPengguna = "";
    String email = "";
    int publicRepo = 0;
    int nFollowers = 0;;
    
    username = x.get(ParserJson.USERNAME,0);
    namaPengguna = x.get(ParserJson.NAMA_PENGGUNA,0);
    email = x.get(ParserJson.EMAIL,0);
    publicRepo = Integer.parseInt(x.get(ParserJson.PUBLIC_REPOS,0));
    nFollowers = Integer.parseInt(x.get(ParserJson.FOLLOWERS,0));
    User result = new User(username, email, namaPengguna, publicRepo, nFollowers);
    return result;
  }
}
