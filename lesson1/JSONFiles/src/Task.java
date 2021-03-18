import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task {
  public static void main(String[] args){
      Task t = new Task();
      JsonObject doc = t.readJson("./resources/restaurant-data.json");
      Database db = new Database(doc);
      System.out.println(db.getRestaurant("Hometown BBQ"));
      System.out.println(db.getAvgReviews("Casa Enrique"));
  }

  public JsonObject readJson(String filename) {
      /* TODO: create a JSON object with the contents of  "filename". You can use the method below to help you read the file. */
      String content = readFile(filename);
      JsonObject json = null;
      try{
          json = (JsonObject)Jsoner.deserialize(content);
//          System.out.println(Jsoner.prettyPrint(content));
      } catch(JsonException e){
          e.printStackTrace();
      }
      return json;
  }

  public String readFile(String filename){
      String content = "";
      try (BufferedReader reader = new BufferedReader(new FileReader(filename))/* Complete this using the parameter passed to this method. */){
          /* Put in code to read the file line by line. */
          String r = reader.readLine();
          while(r != null){
              content += r;
              r = reader.readLine();
          }
      } catch(IOException e) {
          e.printStackTrace();
          System.exit(-1);
      }
      return content;
      /* Put in the variable that should be returned. */
  }
}