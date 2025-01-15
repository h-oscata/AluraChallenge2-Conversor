package alura.model;

import java.util.HashMap;
import java.util.Map;

public class CurrencyResponse{
    private String base_code;
    private Map<String, Double> conversion_rates;

    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }


//    Filtrado de las monedas para las tasas de cambio.
//    Devuelve las tasas filtradas
    public Map<String, Double> filterConversionRates(String[] targetCurrencies) {
        Map<String, Double> filtered = new HashMap<>();
        for (String currency : targetCurrencies) {
            if (conversion_rates.containsKey(currency)) {
                filtered.put(currency, conversion_rates.get(currency));
            }
        }
        return filtered;
    }
}
