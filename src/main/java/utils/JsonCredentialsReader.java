package utils;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.Map;

public class JsonCredentialsReader {
    private Map<String, String> credentials;

    public JsonCredentialsReader() {
        try {
            FileReader reader = new FileReader("src/test/resources/credentials.json");
            credentials = new Gson().fromJson(reader, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return credentials.get("username");
    }

    public String getPassword() {
        return credentials.get("password");
    }
}