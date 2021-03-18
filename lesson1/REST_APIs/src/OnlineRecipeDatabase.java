import json_simple.JsonArray;
import json_simple.JsonException;
import json_simple.JsonObject;
import json_simple.Jsoner;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class OnlineRecipeDatabase {

    String baseUrl = "http://www.recipepuppy.com/api/?";

    public JsonObject getRecipesByIngredients(String ingredients) throws Exception {
        // TODO You have to use the url to retrieve the contents of the website.This will be a String, but in JSON format.
        //Getting the things ready to connect to the web
        return url2json(new URL(baseUrl+"i="+ingredients)); // TODO Remember to return a JSON object.
    }

    public JsonObject getRecipesByDish(String dish) throws Exception {
        //Getting the things ready to connect to the web
        /* TODO
Fill in this data type (Object) */
        // TODO Fill in this datatype (Object)
        return url2json(new URL(baseUrl+"q="+dish));
    }

    public String formatRecipeAsString(JsonObject doc){
        String results = "";
        JsonArray res = (JsonArray) doc.get("results");
        for (int i = 0; i < res.size(); i++) {
            JsonObject r = (JsonObject) res.get(i);
            results += String.format(
                    "Title: %s\nLink: %s\nIngredients: %s\n",
                    r.get("title"),
                    r.get("href"),
                    r.get("ingredients")
            );
        }
        return results;
    }

    public void saveRecipes(String text, String outfile){
        /*
Given a String with some text in it, write that text to a file. 
The name of the file is given in outfile */

        try (FileOutputStream fout = new FileOutputStream(outfile)) {
            fout.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonObject url2json(URL url) throws Exception {
        String contents = "";
        Scanner urlScanner = new Scanner(url.openStream());
        while (urlScanner.hasNextLine()) {
            contents += urlScanner.nextLine();
        }
        urlScanner.close();
        JsonObject json = null;
        try {
            json = (JsonObject)Jsoner.deserialize(contents);
        } catch (JsonException e){
            e.printStackTrace();
        }
        return json;
    }

}
