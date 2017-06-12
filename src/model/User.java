package model;

/**
 * User.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class User {
  private String username;
  private String email;
  private String namaPengguna;
  private Integer nRepo;
  private Integer nFollower;

  public User(){
    username = "";
    email = "";
    namaPengguna = "";
    nRepo = 0;
    nFollower = 0;
  }
  
  public User (String u, String e, String n, Integer r, Integer f){
    username = u;
    email = e;
    namaPengguna = n;
    nRepo = r;
    nFollower = f;
  }
  
  public String getUsername(){
    return username;
  }
  
  public String getEmail(){
    return email;
  }
  
  public String getNamaPengguna(){
    return namaPengguna;
  }
  
  public Integer getNRepo(){
    return nRepo;
  }

  public Integer getNFollower(){
    return nFollower;
  }
  
  public void setData(String u, String e, String n, Integer r, Integer f){
    username = u;
    email = e;
    namaPengguna = n;
    nRepo = r;
    nFollower = f;
  }
}
