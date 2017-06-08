package model;

/**
 * User.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class User {
  private String username;
  private String email;
  private String namaPengguna;

  public User(){
    username = "";
    email = "";
    namaPengguna = "";
  }
  
  public User (String u, String e, String n){
    username = u;
    email = e;
    namaPengguna = n;
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
  
  public void setData(String u, String e, String n){
    username = u;
    email = e;
    namaPengguna = n;
  }
}
