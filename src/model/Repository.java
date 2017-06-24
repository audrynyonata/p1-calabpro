package model;

/**
 * Repository.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class Repository {
  /**
   * Atribut string nama repository.
   */
  private String nama;
  /**
   * Atribut string deskripsi repository.
   */
  private String deskripsi;
  /**
   * Atribut string url menuju repository.
   */
  private String url;
  
  /**
   * Konstruktor.
   */
  public Repository() {
    nama = "";
    deskripsi = "";
    url = "";
  }
  
  /**
   * Konstruktor dengan parameter.
   * @param n nama repository.
   * @param d deskripsi repository.
   * @param u url menuju repository.
   */
  public Repository(String n, String d, String u) {
    nama = n;
    deskripsi = d;
    url = u;
  }

  /**
   * Mengembalikan nama repository.
   * @return string berisi nama.
   */
  public String getNama() {
    return nama;
  }
  
  /**
   * Mengembalikan deskripsi repository.
   * @return string berisi deskripsi.
   */
  public String getDeskripsi() {
    return deskripsi;
  }
  
  /**
   * Mengembalikan url menuju repository.
   * @return string berisi url.
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * Mengubah data yang tersimpan pada atribut.
   * @param n nama repository.
   * @param d deskripsi repository.
   * @param u url menuju repository.
   */
  public void setData(String n, String d, String u) {
    nama = n;
    deskripsi = d;
    url = u;
  }

  /**
   * Mengembalikan string dengan format satu atribut per baris.
   * @return sebuah string tanpa karakter newline di akhir.
   */
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(nama).append(System.getProperty("line.separator"));
    stringBuilder.append(deskripsi).append(System.getProperty("line.separator"));
    stringBuilder.append(url);
    return (stringBuilder.toString());
  }
}
