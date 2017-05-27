package ud.binmonkey.prog3_proyecto_client.https;

import org.json.JSONObject;
import ud.binmonkey.prog3_proyecto_client.common.Pair;
import ud.binmonkey.prog3_proyecto_client.common.network.URI;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HTTPSClient {
    private String host = URI.getHost("https-client");
    private int port = URI.getPort("https-client");

    static {
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals("localhost"));
    }

    public HTTPSClient() {
    }

    /**
     *
     * @param userName username
     * @param passWord password of user with username
     * @return token if succeeded, null if it did not
     * @throws IOException login failed
     */
    public Response login(String userName, String passWord) {
        ArrayList<Pair> args = new ArrayList<>();
        try {
            return HTTPS.sendRequest("https://" + host, port, "/login", Methods.GET, null, null,
                    new Pair<>("username", userName), new Pair<>("password", passWord));
        } catch (IOException e) {
            return null;
        }
    }

    public String sessionInfo(String userName, String token) {
        try {
            return HTTPS.sendRequest("https://" + host, port, "/sessionInfo", Methods.GET, null, null,
                    new Pair<>("username", userName), new Pair<>("token", token)).getContent();
        } catch (IOException e) {
            return null;
        }
    }

    public Response signUp(String userName, String password, String displayName, String email, String birthdate,
                         String gender, String preferred_lang) {
        HashMap<String, String> args = new HashMap<String, String>() {{
            put("username", userName);
            put("password", password);
            put("display_name", displayName);
            put("email", email);
            put("birth_date", birthdate);
            put("gender", gender);
            put("preferred_language", preferred_lang);
        }};

        for (String key: args.keySet()) {
            if (args.get(key) == null || args.keySet().equals("")) {
                args.remove(key);
            }
        }

        Pair[] pairs = new Pair[args.size()];

        int i = 0;
        for (String key: args.keySet()) {
            pairs[i] = new Pair(key, args.get(key));
            i++;
        }

        try {
            return HTTPS.sendRequest("https://" + host, port, "/signUp", Methods.POST,
                    null, null, pairs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response signUp(String userName, char[] password, String displayName, String email, String birthdate,
                         String gender, String preferred_lang) {
        return signUp(userName, new String(password), displayName, email, birthdate, gender, preferred_lang);
    }

    public String userInfo(String userName, String token) {
        try {
            return HTTPS.sendRequest("https://" + host, port, "/userInfo", Methods.GET, null, null,
                    new Pair<>("username", userName), new Pair<>("token", token)).getContent();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String changeProperty(String userName, String property, String value, String token) {
        try {
            return HTTPS.sendRequest("https://" + host, port, "/changeProperty", Methods.GET,
                    null, null,
                    new Pair<>("username", userName), new Pair<>("token", token),
                    new Pair<>("property", property), new Pair<>("value", value)).getContent();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Response listDir(String userName, String directory, String token) {
        Pair[] pairs;
        if (directory == "" || directory == null) {
            pairs = new Pair[] {new Pair("username", userName), new Pair("token", token)};
        } else {
            pairs = new Pair[] {new Pair("username", userName), new Pair("token", token),
                    new Pair("directory", directory)};
        }

        try {
            return HTTPS.sendRequest("https://" + host, port, "/listDir", Methods.GET,
                    null, null, pairs);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject parseDirResponse(Response response) {
        if (response == null) {
            return null;
        }
        return new JSONObject(response.getContent());
    }


    public static void main(String[] args) {
        String userName = "test";
        String passWord = "test";
        HTTPSClient httpsClient = new HTTPSClient();
        String token = httpsClient.login(userName, passWord).getContent();
        if (token == null) {
            System.out.println("Login failed.");
        }
        System.out.println("Token: " + token);
        System.out.println(httpsClient.sessionInfo(userName, token));
        System.out.println(httpsClient.userInfo(userName, token));
        System.out.println(httpsClient.changeProperty(userName, "birth_date", "23-10-1990", token));
        System.out.println(httpsClient.userInfo(userName, token));
        System.out.println(httpsClient.parseDirResponse(httpsClient.listDir(userName, null, token)));

        /* Uncomment the following lines to check user token expiration */
        /*
        try {
            Thread.sleep(6 * 60 * 1000);
        } catch (InterruptedException e) {}
        System.out.println(httpsClient.sessionInfo(userName, token));
        */
    }
}
