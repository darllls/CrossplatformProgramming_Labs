import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class CurrencyAnalyzer {

    static String endDate;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter start date: ");
            String startDate = scanner.next();

            System.out.println("Enter end date: ");
            endDate = scanner.next();

            System.out.println("Enter currency code (e.g., usd, gbp): ");
            String currencyCode = scanner.next().toLowerCase();

            String apiUrl = "https://bank.gov.ua/NBU_Exchange/exchange_site?start=" + startDate + "&end=" + endDate
                    + "&valcode=" + currencyCode + "&sort=exchangedate&order=desc&json";

            try {
                JSONArray currencyData = fetchCurrencyData(new URI(apiUrl));

                analyzeAndPredict(currencyData, currencyCode);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONArray fetchCurrencyData(URI apiUrl) throws IOException {
        URL url = apiUrl.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return new JSONArray(response.toString());
        } finally {
            connection.disconnect();
        }
    }

    private static void analyzeAndPredict(JSONArray currencyData, String currencyCode) {
        System.out.println("Analysis and prediction for " + currencyCode);

        double[] rates = new double[currencyData.length()];
        for (int i = currencyData.length() - 1, j = 0; i >= 0; i--, j++) {
            JSONObject dataPoint = currencyData.getJSONObject(i);
            double rate = dataPoint.getDouble("rate");
            rates[j] = rate;
        }

        double endDateRate = rates[rates.length - 1];
        System.out.println("\n" + endDate + " rate: " + endDateRate);

        double averageRate = calculateAverage(rates);
        System.out.println("Average Rate: " + averageRate);
        double trend = calculateTrend(rates);
        System.out.println("\nTrend: " + trend);
        detectPatternsAndTrends(rates);
        System.out.println("---");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of months to predict: ");
        int numMonths = scanner.nextInt();
        double[] forecast = predictFutureRates(rates, numMonths);
        System.out.println("Forecast for the next" + numMonths + " months:");
        for (int i = 0; i < forecast.length; i++) {
            System.out.println("Month " + (i + 1) + ": " + forecast[i]);
        }
    }

    private static double[] predictFutureRates(double[] rates, int numMonths) {
        int n = rates.length;
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = i;
        }

        double[] forecast = new double[numMonths];
        for (int i = 0, j = numMonths; i < numMonths; i++, j--) {
            forecast[i] = calculateTrend(rates) * Math.exp(i) + rates[rates.length - j];
        }

        return forecast;
    }

    private static void detectPatternsAndTrends(double[] rates) {
        double rateDiff = rates[rates.length - 1] - rates[1];

        if (rateDiff > 0) {
            System.out.println("Pattern: Upward trend");
        } else if (rateDiff < 0) {
            System.out.println("Pattern: Downward trend");
        } else {
            System.out.println("Pattern: No significant change");
        }
    }

    private static double calculateAverage(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    private static double calculateTrend(double[] data) {
        int n = data.length;

        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += i;
            sumY += data[i];
            sumXY += i * data[i];
            sumX2 += i * i;
        }
        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);

        return slope;
    }

}