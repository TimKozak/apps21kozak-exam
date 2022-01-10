package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private final HashMap<String, Json> dict;

    public JsonObject(JsonPair... jsonPairs) {
        this.dict = new HashMap<>();
        for (JsonPair jsonPair : jsonPairs) {
            this.dict.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        List<String> keys = new ArrayList<>(dict.keySet());
        List<Json> values = new ArrayList<>(dict.values());
        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = values.get(i).toJson();
            pairs.add("'" + key + "': " + value);
        }

        String output = String.join(", ", pairs);
        return "{" + output + "}";
    }

    public void add(JsonPair jsonPair) {
        this.dict.put(jsonPair.key, jsonPair.value);
    }

    public boolean contains(String name) {
        return dict.containsKey(name);
    }

    public Json find(String name) {
        if (contains(name)) {
            return dict.get(name);
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject output = new JsonObject();

        for (String name : names) {
            if (contains(name)) {
                output.add(new JsonPair(name, find(name)));
            }
        }

        return output;
    }
}
