package controller;

import model.User;

/**
 * UserController.java.
 * Berisi fungsi untuk mengolah data bertipe user.
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class UserController {
  /**
   * Atribut string username user.
   */
  private String username;
  
  /**
   * Konstruktor.
   */
  public UserController() {
    username = "";
  }
  
  /**
   * Konstruktor dengan parameter.
   * @param u string username yang akan disimpan di atribut.
   */
  public UserController(String u) {
    username = u;
  }
  
  /**
   * Mengembalikan string username yang tersimpan di atribut.
   * @return string username yang tersimpan di atribut.
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Melakukan parse JSON dengan memanggil fungsi get.
   * Jika email dan fullname private, maka bernilai "Tidak ada data".
   * @return bertipe User dengan data lengkap.
   */
  public User getUserInfo() {
    ParserJson x = new ParserJson();
    x.setAsJsonUser(username);
    String username = "";    
    username = x.get(ParserJson.USERNAME,0);
    String namaPengguna = "";
    namaPengguna = x.get(ParserJson.NAMA_PENGGUNA,0);
    String email = "";
    email = x.get(ParserJson.EMAIL,0);
    int publicRepo = 0;
    publicRepo = Integer.parseInt(x.get(ParserJson.PUBLIC_REPOS,0));
    int numFollowers = 0;;
    numFollowers = Integer.parseInt(x.get(ParserJson.FOLLOWERS,0));
    User result = new User(username, email, namaPengguna, publicRepo, numFollowers);
    return result;
  }
}
