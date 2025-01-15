package alura;

import alura.api.ApiClient;
import alura.model.CurrencyResponse;
import alura.service.CurrencyService;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String apiKey = "e7416d1d59a20a0adc0253ae";  // API KEY
        ApiClient apiClient = new ApiClient(apiKey);
        CurrencyService currencyService = new CurrencyService();

        // Formato para 2 decimales
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Monedas a convertir
        String[] targetCurrencies = {"USD", "ARS", "BRL", "COP"};

        try {
            CurrencyResponse response = apiClient.getExchangeRates("USD");
            Map<String, Double> filteredRates = response.filterConversionRates(targetCurrencies);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Menu para el usuario
                System.out.println("********************************************");
                System.out.println("Sea bienvenido/a al Conversor de Moneda");
                System.out.println();
                System.out.println("1) Dólar           =>>  Peso argentino");
                System.out.println("2) Peso argentino  =>>  Dólar");
                System.out.println("3) Dólar           =>>  Real brasileño");
                System.out.println("4) Real brasileño  =>>  Dólar");
                System.out.println("5) Dólar           =>>  Peso colombiano");
                System.out.println("6) Peso colombiano =>>  Dólar");
                System.out.println("7) Salir");
                System.out.println();
                System.out.println("Elija una opción válida: ");
                System.out.println("********************************************");

                int opcion = scanner.nextInt();

                if (opcion == 7) {
                    System.out.println("¡Gracias por usar el CONVERSOR DE MONEDAS ALURA!");
                    break;
                }

                System.out.println("Ingrese el monto a convertir: ");
                double amount = scanner.nextDouble();

                double result = 0;
                switch (opcion) {
                    case 1:
                        result = currencyService.convert(amount, "USD", "ARS", filteredRates);
                        System.out.println("CONVERSIÓN: "+amount + " USD ==>> " + decimalFormat.format(result) + " ARS.");
                        break;
                    case 2:
                        result = currencyService.convert(amount, "ARS", "USD", filteredRates);
                        System.out.println("CONVERSIÓN: "+amount + " ARS ==>> " + decimalFormat.format(result) + " USD.");
                        break;
                    case 3:
                        result = currencyService.convert(amount, "USD", "BRL", filteredRates);
                        System.out.println("CONVERSIÓN: "+amount + " USD ==>> " + decimalFormat.format(result) + " BRL.");
                        break;
                    case 4:
                        result = currencyService.convert(amount, "BRL", "USD", filteredRates);
                        System.out.println("CONVERSIÓN: "+amount + " BRL ==>> " + decimalFormat.format(result) + " USD.");
                        break;
                    case 5:
                        result = currencyService.convert(amount, "USD", "COP", filteredRates);
                        System.out.println("CONVERSIÓN: "+amount + " USD ==>> " + decimalFormat.format(result) + " COP.");
                        break;
                    case 6:
                        result = currencyService.convert(amount, "COP", "USD", filteredRates);
                        System.out.println("CONVERSIÓN: "+amount + " COP ==>> " + decimalFormat.format(result) + " USD.");
                        break;
                    default:
                        System.out.println("Opción invalida. Por favor, ingrese otro número:");
                }
                System.out.println();
            }

            scanner.close();

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}