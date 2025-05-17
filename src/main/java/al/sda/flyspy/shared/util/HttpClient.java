package al.sda.flyspy.shared.util;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

public class HttpClient {
    private CloseableHttpClient httpClient;
    private static final HttpClient INSTANCE = new HttpClient();

    private HttpClient() {
        this.httpClient = HttpClientBuilder.create()
                .build();
    }

    public static HttpClient getInstance() {
        return INSTANCE;
    }

    public CloseableHttpClient getHttpClient() {
        return this.httpClient;
    }

}
