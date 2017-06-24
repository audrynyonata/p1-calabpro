package model;

/**
 * User.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class User {
  /**
   * Atribut string username user.
   */
  private String username;
  /**
   * Atribut string alamat email user.
   */
  private String email;
  /**
   * Atribut string nama pengguna user.
   */
  private String namaPengguna;
  /**
   * Atribut Integer jumlah repository publik user.
   */
  private Integer numRepo;
  /**
   * Atribut Integer jumlah follower user.
   */
  private Integer numFollower;

  /**
   * Konstruktor.
   */
  public User() {
    username = "";
    email = "";
    namaPengguna = "";
    numRepo = 0;
    numFollower = 0;
  }

  /**
   * Konstruktor dengan parameter.
   * @param u username.
   * @param e email.
   * @param n nama pengguna.
   * @param r jumlah repository publik.
   * @param f jumlah followers.
   */
  public User(String u, String e, String n, Integer r, Integer f) {
    username = u;
    email = e;
    namaPengguna = n;
    numRepo = r;
    numFollower = f;
  }
  
  /**
   * Mengembalikan username user.
   * @return string berisi username.
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Mengembalikan alamat email user.
   * @return string berisi email.
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Mengembalikan nama pengguna user.
   * @return string berisi nama pengguna.
   */
  public String getNamaPengguna() {
    return namaPengguna;
  }
  
  /**
   * Mengembalikan jumlah repository publik user.
   * @return Integer jumlah repository publik.
   */
  public Integer getNRepo() {
    return numRepo;
  }

  /**
   * Mengembalikan jumlah follower user.
   * @return Integer jumlah follower.
   */
  public Integer getNFollower() {
    return numFollower;
  }
  
  /**
   * Mengubah data yang tersimpan pada atribut.
   * @param u username.
   * @param e email.
   * @param n nama pengguna.
   * @param r jumlah repository publik.
   * @param f jumlah followers.
   */
  public void setData(String u, String e, String n, Integer r, Integer f) {
    username = u;
    email = e;
    namaPengguna = n;
    numRepo = r;
    numFollower = f;
  }
}
