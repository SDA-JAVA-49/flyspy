package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightDataResponse;
import al.sda.flyspy.shared.util.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AirDataServiceImpl implements AirDataService {
    private static final Logger LOGGER = Logger.getLogger(AirDataServiceImpl.class.getName());
    private static final String API_KEY = "0b05d019415b4f836faea0a80b52a9ff";
    private static final String URL = "https://api.aviationstack.com/v1/flights";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public FlightDataResponse getFlights(Map<String, String> params) {
        params.put("access_key", API_KEY);
        try {
            URIBuilder builder = new URIBuilder(URL);
            params.forEach(builder::addParameter);
            String url = builder.build().toString();

            CloseableHttpResponse httpResponse = HttpClient.getInstance()
                    .getHttpClient()
                    .execute(new HttpGet(url));
            String jsonResponse = EntityUtils.toString(httpResponse.getEntity());
            LOGGER.info("Response code: " + httpResponse.getCode());
            LOGGER.info("Response body: " + jsonResponse);
            return MAPPER.readValue(jsonResponse, FlightDataResponse.class);

        } catch (MalformedURLException | URISyntaxException e) {
            MessageFormat messageFormat = new MessageFormat("Could not format url properly. {0}");
            LOGGER.severe(messageFormat.format(e));
            throw new RuntimeException(e);
        } catch (IOException e) {
            MessageFormat messageFormat = new MessageFormat("There was an issue fetching data from 3-rd party API. {}");
            LOGGER.severe(messageFormat.format(e));
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
