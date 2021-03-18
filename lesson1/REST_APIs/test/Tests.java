import json_simple.JsonArray;
import json_simple.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class Tests {
  @Test
  public void testSolution() {
    // put your test here
    OnlineRecipeDatabase ord = new OnlineRecipeDatabase();
    JsonObject obj1 = null;
    JsonObject obj2 = null;
    JsonArray arr1 = null;
    JsonArray arr2 = null;
    String list=null;
    try {
      obj1 = ord.getRecipesByDish("artichoke");
      obj2 = ord.getRecipesByIngredients("cheese,mayonnaise");
      int elems = obj1.keySet().size();
      arr1 = (JsonArray) obj1.get("results");
      arr2 = (JsonArray) obj2.get("results");
      String site = (String)obj1.get("title");
      String oneDish = (String)((JsonObject)arr2.get(0)).get("ingredients");
      list = ord.formatRecipeAsString(obj2);
      ord.saveRecipes(list,"test.txt");
      Assert.assertEquals("Are you reading/converting the full JSON document?",4,elems);
      Assert.assertTrue("Are your recipes by dish coming as a JsonArray?",arr1.size()>6 && arr1.size()<11);
      Assert.assertTrue("Are your recipes by ingredient coming as a JsonArray?",arr2.size()>6 && arr1.size()<11);
      Assert.assertTrue("Are you sure you are querying the right thing?", oneDish.contains("cheese"));
      Assert.assertTrue("Are you returning the full JsonObject?",site.equals("Recipe Puppy"));
      Assert.assertTrue("Are you formatting the Json as String correctly?",list.contains(oneDish));
    }catch(Exception e){
      Assert.fail(e.getMessage());
    }

    try{
      File f = new File("test.txt");
      Assert.assertTrue("Are you sure you are saving a file with the name that comes in the parameter?",f.exists());
      Assert.assertEquals("Are you writing the file as directed?",f.length(),list.length());
    }catch(SecurityException e){
      Assert.fail("Are you saving the file with ANY filename that is passed?");
    }

  }
}