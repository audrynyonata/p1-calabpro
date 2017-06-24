package controller;

import model.Repository;
import model.User;

/**
 * RepositoryController.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class RepositoryController {
  private static User owner;
  
  public RepositoryController() {
    owner = new User();
  }
  
  public RepositoryController(User o) {
    owner = o;
  }
  
  public User getOwner() {
    return owner;
  }
  
  /**
   * Mengembalikan array berelemen repository dari owner.
   * Jika tidak memiliki repository publik, maka array berukuran nol.
   * @return sebuah array berisi repository publik.
   */
  public Repository[] getRepos() {
    ParserJson x = new ParserJson();
    x.setAsJsonRepo(owner);
    Integer count = owner.getNRepo();
    Repository[] result = new Repository[count];
    if (count > 0) {
      String lastElement = "";
      String nama = "";
      String deskripsi = "";
      String url = "";
      int fromIndex = x.getString().indexOf("\"id\"");
      for (int n = 0; n < count; n++) {
        if (n > 0) {
          fromIndex = x.getString().indexOf(lastElement,fromIndex) + 1;
        }
        lastElement = x.get(ParserJson.DEFAULT_BRANCH,fromIndex);
        nama = x.get(ParserJson.NAMA_REPOSITORY,fromIndex);
        deskripsi = x.get(ParserJson.DESCRIPTION,fromIndex);
        url = x.get(ParserJson.HTML_URL,fromIndex);
        result[n] = new Repository(nama, deskripsi, url);
      }
    }
    return result;
  }
}
