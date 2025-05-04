package src.main.com.conversordemoeda;

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

                System.out.println("=== Análise da Resposta HTTP ===");
                System.out.println("Código de Status: " + response.statusCode());
                System.out.println("URI da Resposta: " + response.uri());
                System.out.println("Versão HTTP: " + response.version());

                System.out.println("\nCabeçalhos da Resposta:");
                response.headers().map().forEach((key, values) ->
                        System.out.println(key + ": " + values));

                String contentType = response.headers().firstValue("Content-Type").orElse("Desconhecido");
                System.out.println("\nTipo de Conteúdo: " + contentType);


                    System.out.println(response.body());
            sc.close();
    }
}