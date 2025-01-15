package alura.service;

import java.util.Map;

public class CurrencyService {

//  Metodo para convertir el monto de una moneda considerando:
//    -la moneda inicial (initialCurrency)
//    -la moneda final (finalCurrency)
//    -las tasas de conversion
    public double convert(double amount, String initialCurrency, String finalCurrency, Map<String, Double> rates) {
        if (!rates.containsKey(initialCurrency) || !rates.containsKey(finalCurrency)) {
            throw new IllegalArgumentException("ERROR: No se encontraron datos de esta moneda.");
        }

        double initialRate = rates.get(initialCurrency);
        double finalRate = rates.get(finalCurrency);

        // Conversión de la moneda
        return (amount / initialRate) * finalRate;
    }
}

