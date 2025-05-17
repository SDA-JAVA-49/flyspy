package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.ApiResponse;
import al.sda.flyspy.shared.fault.ExternalServiceException;
import org.apache.hc.core5.net.URIBuilder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class AirDataServiceImpl implements AirDataService{
    private static final String API_KEY = "ef79d424a7dc7a3c385f5ca3cd04e5b5";
    private static final String BASE_URL = "https://api.aviationstack.com/v1/flights?access_key=" + API_KEY;


        @Override
    public List<AirData> getFlights(Map<String, String> params) {
            try {
                URIBuilder uriBuilder = new URIBuilder(BASE_URL);
                HttpClient client = HttpClient.newHttpClient();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }

                URI uri = uriBuilder.build();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
                ObjectMapper mapper = new ObjectMapper();
                ApiResponse root = mapper.readValue(response.body(), ApiResponse.class);

                return root.getData();
            } catch (IOException | InterruptedException e) {
                throw new ExternalServiceException();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
}
