package controller.accessor;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Base64;

import static java.net.HttpURLConnection.HTTP_OK;

public class SpotifyAuthorization {
    private static final String ACCOUNT_BASE_URI = "https://accounts.spotify.com/api/token";
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;

    public SpotifyAuthorization(String clientId, String clientSecret){
        CLIENT_ID = clientId;
        CLIENT_SECRET = clientSecret;
    }


    public static Token get() throws Exception {
        Token token = gson().fromJson(responseOf(request()), Token.class);
        token.updateExpiration();
        return token;
    }

    private static HttpRequest request() {
        return HttpRequest.newBuilder()
                .uri(URI.create(ACCOUNT_BASE_URI))
                .header("Authorization", "Basic " + SpotifyAuthorization.id())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();
    }

    private static String id() {
        return Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
    }

    private static String responseOf(HttpRequest request) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != HTTP_OK) throw new Exception(" Http response error " + response.statusCode());
        return response.body();
    }

    public static class Token {
        public final String access_token;
        public final String token_type;
        public int expires_in = 0;
        private Instant expiration;

        public Token(String access_token, String token_type) {
            this.access_token = access_token;
            this.token_type = token_type;
            this.expires_in = expires_in;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "access_token='" + access_token + '\'' +
                    ", token_type='" + token_type + '\'' +
                    ", expires_in=" + expires_in +
                    '}';
        }

        public void updateExpiration() {
            long epochSecond = Instant.now().getEpochSecond() + expires_in;
            expiration = Instant.ofEpochSecond(epochSecond);
        }

        public boolean isExpired() {
            return expiration.isBefore(Instant.now());
        }

        public boolean isValid() {
            return !isExpired();
        }
    }

    private static Gson gson() {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

}
