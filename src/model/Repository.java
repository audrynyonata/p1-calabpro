package model;

/**
 * Repository.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class Repository {
  private String nama;
  private String deskripsi;
  private String url;
  
  public Repository(){
    nama = "";
    deskripsi = "";
    url = "";
  }
  
  public Repository(String n, String d, String u){
    nama = n;
    deskripsi = d;
    url = u;
  }

  public String getNama(){
    return nama;
  }
  
  public String getDeskripsi(){
    return deskripsi;
  }
  
  public String getUrl(){
    return url;
  }
  
  public void setData(String n, String d, String u){
    nama = n;
    deskripsi = d;
    url = u;
  }  
}
