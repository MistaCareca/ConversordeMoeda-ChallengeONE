package src.main.com.conversordemoeda;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cliente_api {
        private static void coverterValor(ConversaoResponse conversao, double valor, Scanner sc){
            if(valor < 0){
                System.out.println("Erro: O valor a converter dever ser positivo.");
                return;
            }
            double resultado = valor * conversao.getConversionRate();
            System.out.printf("Resultado de conversao: %.2f %s = %.2f %s%n",
                    valor, conversao.getBaseCode(), resultado, conversao.getTargetCode());
        }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();

        List<String> moedasPermitidas = Arrays.asList("ARS", "BOB", "BRL", "CLP", "USD", "COP");

        try{
            System.out.println("Moedas disponiveis: ARS, BOB, BRL, CLP, USD, COP");
            System.out.println("Informe a sigla da moeda de origem (ex: USD): ");
            String moedaOrigem = sc.nextLine().toUpperCase();
            System.out.println("Informe a sigla da moeda de destino (ex: BRL): ");
            String moedaDestino = sc.nextLine().toUpperCase();

            if(!moedasPermitidas.contains(moedaOrigem) || !moedasPermitidas.contains(moedaDestino)){
                System.out.println("Erro: Moeda inválida. Escolha entre: " + moedasPermitidas);
                return;
            }
            String apiKey = "Sua-API-Key";
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

            if(response.statusCode() != 200){
                System.out.println("Erro na requisiçao: codigo: " + response.statusCode());
                return;
            }
            ConversaoResponse conversao = gson.fromJson(response.body(), ConversaoResponse.class);
            System.out.println("\n=== Resultados (Mapeamento com Gson) ===");
            if (conversao.getErrorType() != null){
                System.out.println("Erro da API: " + conversao.getErrorType());
                return;
            }
            System.out.printf("Taxa de câmbio: 1 %s = %.4f %s%n",
                    conversao.getBaseCode(), conversao.getConversionRate(), conversao.getTargetCode());

            System.out.println("\nInforme o valor a covnerter (ex:5.50): ");
            double valor;
            try{
                valor = sc.nextDouble();
            }  catch (Exception e){
                System.out.println("Erro: valor invalido. use numeros (ex: 5.50)");
                return;
            }

            coverterValor(conversao, valor, sc);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao fazer a requisiçao: " + e.getMessage());
        } catch (com.google.gson.JsonSyntaxException e) {
            System.out.println("Erro: Formato JSON invalido na resposta");
        } finally {
        sc.close();
        }
    }

}