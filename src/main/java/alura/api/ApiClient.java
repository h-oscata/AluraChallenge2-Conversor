package alura.api;

import alura.model.CurrencyResponse;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiClient {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;
    private final HttpClient httpClient;

    //Constructor que requiere la APIKEY para instanciar
    public ApiClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    //Solicitud GET para obtener las tasas de cambio de una moneda(baseCurrency)
    public CurrencyResponse getExchangeRates(String initialCurrency) throws Exception {
        String urlString = buildRequestUrl(initialCurrency);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("ERROR EN LA SOLICITUD: Código " + response.statusCode());
        }

        Gson gson = new Gson();
        return gson.fromJson(response.body(), CurrencyResponse.class);
    }

    //Metodo para construir la URL para la solitud a la API
    private String buildRequestUrl(String initialCurrency) {
        return BASE_URL + apiKey + "/latest/" + initialCurrency;
    }
}
