import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Database {
    private JsonObject json;

    public Database(JsonObject data){
        json = data;
    }

    public JsonObject getRestaurant(String name){
        JsonArray restaurants = (JsonArray) json.get("restaurants");
        int res_ind;
        for (res_ind = 0; res_ind < restaurants.size() - 1; res_ind++) {
            if (((JsonObject) restaurants.get(res_ind)).get("name").equals(name))
                break;
        }
        return (JsonObject) restaurants.get(res_ind);
    }

    public double getAvgReviews(String name){
        BigDecimal avg = new BigDecimal(0);
        JsonObject res = getRestaurant(name);
        JsonArray revs = (JsonArray) res.get("reviews");
        for (int i = 0; i < revs.size(); i++) {
            JsonObject rev = (JsonObject) revs.get(i);
            avg = avg.add((BigDecimal)rev.get("rating"));
        }
        avg = avg.divide(BigDecimal.valueOf(revs.size()), 2, RoundingMode.HALF_UP);
        return avg.doubleValue();
    }
}
