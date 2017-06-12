package controller;

import view.UserView;
import model.Repository;
import model.User;

/**
 * RepositoryController.java
 * @author NIM/Nama: 13515087/Audry Nyonata.
 */

public class RepositoryController {
  private static User owner;
  
  public RepositoryController(){
    owner = new User();
  }
  
  public RepositoryController(User o){
    owner = o;
  }
  
  public Repository[] getRepos(){
    ParserJson x = new ParserJson();
    x.setAsJsonRepo(owner);
    String lastElement = "";
    String nama = "";
    String deskripsi = "";
    String url = "";
    Integer count = owner.getNRepo();
    Repository[] result = new Repository[count];
    int fromIndex = 0;
    for (int n = 0; n < count; n++){
      if (n > 0){
        fromIndex = x.getString().indexOf(lastElement,fromIndex) + 1;
      }
      lastElement = x.get(ParserJson.DEFAULT_BRANCH,fromIndex);
      nama = x.get(ParserJson.NAMA_REPOSITORY,fromIndex);
      deskripsi = x.get(ParserJson.DESCRIPTION,fromIndex);
      url = x.get(ParserJson.HTML_URL,fromIndex);
      result[n] = new Repository(nama, deskripsi, url);
    }
    return result;
  }

  public static void main (String[] args){
    UserView uv = new UserView("audrynyonata");
    uv.setVisible(true);
  }
}
