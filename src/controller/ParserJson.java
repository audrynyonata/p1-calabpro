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
 * ParserJson.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class ParserJson {
  private static final String githubUrl = "https://api.github.com";
  
  public static final int DEFAULT_BRANCH = 0;
  public static final int TOTAL_COUNT = 1;
  public static final int USERNAME = 2;
  public static final int EMAIL = 3;
  public static final int NAMA_PENGGUNA = 4;
  public static final int PUBLIC_REPOS = 5;
  public static final int FOLLOWERS = 6;
  public static final int NAMA_REPOSITORY = 7;
  public static final int DESCRIPTION = 8;
  public static final int HTML_URL = 9;
  public static final int SITE_ADMIN = 10;
  
  public static final int FILTER_NONE = -1;

  private String s;

  public ParserJson(){
    s = "";
  }

  public ParserJson(String string){
    s = string;
  }
  
  public ParserJson (ParserJson p){
    s = p.s;
  }
  
  public String getString(){
    return s;
  }

  public static String getFromUrl(String searchUrl){
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
      connection.setReadTimeout(15*1000);
      connection.connect();
      
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      stringBuilder = new StringBuilder();
      
      String line = null;
      while ((line = reader.readLine())!=null){
        stringBuilder.append(line + "\n");
      }
      reader.close();
      return (stringBuilder.toString());
    } catch (UnknownHostException u) {
      JOptionPane.showMessageDialog(new JFrame(), "Tidak ada koneksi internet. Harap coba lagi.", "Error: Lost Internet Connection", JOptionPane.ERROR_MESSAGE);
    } catch (IOException i) {
      JOptionPane.showMessageDialog(new JFrame(), "Otentikasi gagal. Harap periksa token.", "Error: Bad Authentication", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ("");
  }
  
  public void setAsJsonSearch(String searchKey, String searchIn, Integer nr, Integer nf){
    StringBuilder searchUrl = new StringBuilder();
    searchUrl.append(githubUrl + "/search/users?q=" + searchKey + "+in:" + searchIn);
    if (nr != FILTER_NONE){
      searchUrl.append("+repos:%3E" + nr.toString());
    }
    if (nf != FILTER_NONE){
      searchUrl.append("+followers:%3E" + nf.toString());
    }
    searchUrl.append("&per_page=100");
    s = getFromUrl(searchUrl.toString());
  }
  
  public void setAsJsonUser(String username){
    s = getFromUrl(githubUrl+"/users/"+username);
  }
 
  public void setAsJsonRepo(User owner){
    StringBuilder reposUrl = new StringBuilder();
    reposUrl.append(githubUrl+"/users/"+owner.getUsername()+"/repos?per_page=100");
    String temp = getFromUrl(reposUrl.toString());
    
    char[] arr = temp.toCharArray();
    while (temp.contains("\"owner\"")){
      int i = temp.indexOf("\"owner\"");
      int j = temp.indexOf("\"private\"",i);
      for (int k=i; k<j; k++){
        arr[k] = ' ';
      }
      temp = new String(arr);
    }
    s = new String(temp);
  }
  
  public String get(int constant, int fromIndex){
    String key = null;
    if (!s.equals("")){
      switch (constant){
        case TOTAL_COUNT : key = "total" + "_" + "count"; break;
        case USERNAME : key = "login"; break;
        case EMAIL : key = "email"; break;
        case NAMA_PENGGUNA : key = "name"; break;
        case PUBLIC_REPOS : key = "public_repos"; break;
        case FOLLOWERS : key = "followers"; break;
        case NAMA_REPOSITORY : key = "name"; break;
        case DESCRIPTION : key = "description"; break;
        case HTML_URL : key = "html_url"; break;
        case SITE_ADMIN : key = "site_admin"; break;
        default : key = "default_branch"; break;
      }
      key = "\"" + key + "\":";
      int lhs = s.indexOf(key, fromIndex);
      int j = key.length();
      int rhs = s.indexOf(",", lhs+j);
      String result = s.substring(lhs+j,rhs);
      if (result.charAt(0) == '"'){
        result = result.substring(1, result.length()-1);
      } else if (result.equals("null")){
        result = "Tidak ada data";
      }
      return result;
    } else {
      return ("Tidak ada data");
    }
  }
  
  public String toString(){
    return s;
  }
}
