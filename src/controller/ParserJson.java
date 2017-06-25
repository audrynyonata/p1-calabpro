package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Base64;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.User;

/**
 * ParserJson.java.
 * Berisi konstanta, access token, dan fungsi yang dibutuhkan
 * untuk koneksi internet serta mengolah hasilnya (string JSON). 
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class ParserJson {
  /**
   * Konstanta string untuk mengakses GithubAPI (https://api.github.com).
   */
  private static final String githubUrl = "https://api.github.com";
  
  /**
   * Konstanta kode untuk mencari string "default_branch".
   */
  public static final int DEFAULT_BRANCH = 0;
  /**
   * Konstanta kode untuk mencari string "total_count".
   */
  public static final int TOTAL_COUNT = 1;
  /**
   * Konstanta kode untuk mencari string "login".
   */
  public static final int USERNAME = 2;
  /**
   * Konstanta kode untuk mencari string "email".
   */
  public static final int EMAIL = 3;
  /**
   * Konstanta kode untuk mencari string "name".
   */
  public static final int NAMA_PENGGUNA = 4;
  /**
   * Konstanta kode untuk mencari string "public_repos".
   */
  public static final int PUBLIC_REPOS = 5;
  /**
   * Konstanta kode untuk mencari string "followers".
   */
  public static final int FOLLOWERS = 6;
  /**
   * Konstanta kode untuk mencari string "name".
   */
  public static final int NAMA_REPOSITORY = 7;
  /**
   * Konstanta kode untuk mencari string "description".
   */
  public static final int DESCRIPTION = 8;
  /**
   * Konstanta kode untuk mencari string "html_url".
   */
  public static final int HTML_URL = 9;
  /**
   * Konstanta kode untuk mencari string "site_admin".
   */
  public static final int SITE_ADMIN = 10;
  
  /**
   * Konstanta kode yang melambangkan pencarian tidak menggunakan filter.
   */
  public static final int FILTER_NONE = -1;

  /**
   * Atribut string berformat JSON.
   */
  private String string;

  /**
   * Konstruktor.
   * Set atribut dengan null string.
   */
  public ParserJson() {
    string = "";
  }

  /**
   * Konstruktor dengan parameter.
   * @param s string yang nilainya akan disimpan di atribut.
   */
  public ParserJson(String s) {
    string = s;
  }
  
  /**
   * Copy Constructor.
   * @param p bertipe ParserJson yang akan dicopy.
   */
  public ParserJson(ParserJson p) {
    string = p.string;
  }
  
  /**
   * Mengembalikan data string yang tersimpan di atribut.
   * @return string.
   */
  public String getString() {
    return string;
  }

  /**
   * Mengembalikan konstanta string githubUrl.
   * @return string githubUrl.
   */
  public String getGithubUrl() {
    return githubUrl;
  }
  
  /**
   * Mengembalikan string format JSON dari GET sebuah url.
   * Melempar exception offline (UnknownHost) dan 401 error (IOException).
   * @param searchUrl string berisi url yang akan diakses.
   * @return string berformat JSON hasil GET searchUrl.
   */
  public String getFromUrl(String searchUrl) {
    String credentials1 = "audrynyonata";
    String credentials2 = "d48fc53efc21270629ea";
    String credentials3 = "9b675c261efe4739e669";
    String credentials = credentials1 + ":" + credentials2 + credentials3;
    URL url = null;
    BufferedReader reader = null;
    StringBuilder stringBuilder;
 
    try {
      url = new URL(searchUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      String basicAuth = "Basic " + new String(Base64.getEncoder().encode(credentials.getBytes()));
      connection.setRequestProperty("Authorization", basicAuth);
      connection.setRequestMethod("GET");
      connection.setReadTimeout(15 * 1000);
      connection.connect();
      
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      stringBuilder = new StringBuilder();
      
      String line = null;
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line + "\n");
      }
      reader.close();
      return (stringBuilder.toString());
    } catch (UnknownHostException u) {
      JOptionPane.showMessageDialog(new JFrame(), 
          "Tidak ada koneksi internet. Harap coba lagi.", 
          "Error: Lost Internet Connection", JOptionPane.ERROR_MESSAGE);
    } catch (IOException i) {
      JOptionPane.showMessageDialog(new JFrame(),
          "Otentikasi gagal. Harap periksa token.",
          "Error: Bad Authentication", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ("");
  }
  
  /**
   * Memanggil fungsi getFromUrl dengan query search sebagai url.
   * @param searchKey string kata kunci.
   * @param searchIn string mode pencarian: login, email, fullname.
   * @param nr Integer filter jumlah repository minimum.
   * @param nf Integer filter jumlah follower minimum.
   */
  public void setAsJsonSearch(String searchKey, String searchIn, Integer nr, Integer nf) {
    StringBuilder searchUrl = new StringBuilder();
    searchUrl.append(githubUrl + "/search/users?q=" + searchKey + "+in:" + searchIn);
    if (nr != FILTER_NONE) {
      searchUrl.append("+repos:%3E" + nr.toString());
    }
    if (nf != FILTER_NONE) {
      searchUrl.append("+followers:%3E" + nf.toString());
    }
    searchUrl.append("&per_page=100");
    string = getFromUrl(searchUrl.toString());
  }
  
  /**
   * Memanggil fungsi getFromUrl dengan query username sebagai url.
   * @param username string username yang ingin diakses.
   */
  public void setAsJsonUser(String username) {
    string = getFromUrl(githubUrl + "/users/" + username);
  }
 
  /**
   * Memanggil fungsi getFromUrl dengan query repository dari seorang user.
   * @param owner bertipe User yang akan diambil username dan repo publiknya.
   */
  public void setAsJsonRepo(User owner) {
    StringBuilder reposUrl = new StringBuilder();
    reposUrl.append(githubUrl + "/users/" + owner.getUsername() + "/repos?per_page=100");
    String temp = getFromUrl(reposUrl.toString());
    
    char[] arr = temp.toCharArray();
    while (temp.contains("\"owner\"")) {
      int i = temp.indexOf("\"owner\"");
      int j = temp.indexOf("\"private\"",i);
      for (int k = i; k < j; k++) {
        arr[k] = ' ';
      }
      temp = new String(arr);
    }
    string = new String(temp);
  }
  
  /** 
   * Fungsi get mengembalikan string value dari field JSON.
   * @param constant integer konstanta untuk nama field.
   * @param fromIndex integer indeks mulainya pencarian.
   * @return string berupa value (sisi kanan tanda titik dua JSON).
   */
  public String get(int constant, int fromIndex) {
    String key = null;
    if (!string.equals("")) {
      switch (constant) {
        case TOTAL_COUNT : key = "total" + "_" + "count"; 
          break;
        case USERNAME : key = "login"; 
          break;
        case EMAIL : key = "email"; 
          break;
        case NAMA_PENGGUNA : key = "name"; 
          break;
        case PUBLIC_REPOS : key = "public_repos"; 
          break;
        case FOLLOWERS : key = "followers"; 
          break;
        case NAMA_REPOSITORY : key = "name"; 
          break;
        case DESCRIPTION : key = "description"; 
          break;
        case HTML_URL : key = "html_url"; 
          break;
        case SITE_ADMIN : key = "site_admin"; 
          break;
        default : key = "default_branch"; 
          break;
      }
      key = "\"" + key + "\":";
      int lhs = string.indexOf(key, fromIndex);
      int j = key.length();
      int rhs = string.indexOf(",", lhs + j);
      String result = string.substring(lhs + j,rhs);
      if (result.charAt(0) == '"') {
        result = result.substring(1, result.length() - 1);
      } else if (result.equals("null")) {
        result = "Tidak ada data";
      }
      return result;
    } else {
      return ("Tidak ada data");
    }
  }
  
  /**
   * Mengembalikan data yang tersimpan dalam atribut dalam tipe string.
   * @return string.
   */
  public String toString() {
    return string;
  }
}
