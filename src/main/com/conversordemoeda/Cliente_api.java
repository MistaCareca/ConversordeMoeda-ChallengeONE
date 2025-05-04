package src.main.com.conversordemoeda;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class Cliente_api {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();

        System.out.println("Informe a sigla da moeda de origem (ex: USD): ");
        String moedaOrigem = sc.nextLine().toUpperCase();
        System.out.println("Informe a sigla da moeda de destino (ex: BRL): ");
        String moedaDestino = sc.nextLine().toUpperCase();

        String apiKey = "";
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",
                apiKey, moedaOrigem, moedaDestino);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "application/json")
                .header("User-Agent", "ConversordeMoeda/0.1")
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = cliente.send(request,
                HttpResponse.BodyHandlers.ofString());

        ConversaoResponse conversao = gson.fromJson(response.body(), ConversaoResponse.class);
        System.out.println("\n=== Resultados (Mapeamento com Gson) ===");
        System.out.printf("Taxa de câmbio: 1 %s = %.4f %s%n",
                conversao.getBaseCode(), conversao.getConversionRate(), conversao.getTargetCode());

        sc.close();
    }
}