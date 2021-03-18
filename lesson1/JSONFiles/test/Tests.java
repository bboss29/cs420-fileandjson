import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class Tests {
  @Test
  public void testSolution() {
    Task t = new Task();
    JsonObject doc = t.readJson("./test/restaurant-data.json");
    Database db = new Database(doc);
    // put your test here
    String contents = t.readFile("./test/restaurant-data.json");
    Assert.assertEquals("Your readFile method is not reading the file correctly.",20423,contents.length());
    Assert.assertTrue("Are you sure you loaded a JsonObject?",doc.containsKey("restaurants"));
    JsonArray restaurants = (JsonArray)doc.get("restaurants");
    Assert.assertEquals("Loaded wrong number of restaurants",10,restaurants.size());
    JsonObject obj = db.getRestaurant("Kang Ho Dong Baekjeong");
    JsonObject kang = (JsonObject)restaurants.get(2);
    Assert.assertEquals("Your Get Restaurant method is not working correctly. Try printing out the outputs",kang,obj);
    Assert.assertEquals("Maybe the key value pairs are not quite correct. Check Kang Ho Dong Baekjeong",new BigDecimal("3"),(BigDecimal) obj.get("id"));
    Assert.assertEquals("You are not computing the average review score correctly. Check 'Superiority Burger' for example.",4.3,db.getAvgReviews("Superiority Burger"),0.05);
    Assert.assertEquals("It seems the number of fields for some restaurants is wrong. Check how you are reading/converting the file to Json and check Kang Ho Dong Baekjeong",9,db.getRestaurant("Kang Ho Dong Baekjeong").size());
  }
}