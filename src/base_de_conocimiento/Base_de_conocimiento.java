package base_de_conocimiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Base_de_conocimiento {
    private static Map<Integer, Node> allHypothesis = new HashMap<>();
    private static List<Boolean> conditionsResults = new ArrayList<>();

    public static boolean validateRule(Map<String, String> condition) {
        List<Map<String, String>> facts = List.of(
            // Hechos del pingüino
            Map.of("Plumas", "true"),
            Map.of("Vuela", "false"),
            Map.of("Nada", "true"),
            Map.of("Color", "blanco_negro"),

            // Hechos de la gallina
            Map.of("Plumas", "true"),
            Map.of("Vuela", "false"),
            Map.of("Nada", "false"),
            Map.of("Color", "blanco_marron"),

            // Hechos del canario (faltará el hecho de "Canta" para dar falso)
            Map.of("Plumas", "true"),
            Map.of("Vuela", "true"),
            Map.of("Color", "amarillo"),

            // Hechos del avestruz
            Map.of("Plumas", "true"),
            Map.of("Vuela", "false"),
            Map.of("Patas_Largas", "true"),

            // Hechos del flamenco (faltará el color para dar falso)
            Map.of("Plumas", "true"),
            Map.of("Vuela", "true"),
            Map.of("Patas_Largas", "true"),

            // Hechos del pez payaso (dará verdadero)
            Map.of("Aletas", "true"),
            Map.of("Color", "naranja_blanco"),
            Map.of("Nada", "true"),

            // Hechos del delfín (dará verdadero)
            Map.of("Aletas", "true"),
            Map.of("Respira_Aire", "true"),
            Map.of("Inteligencia", "alta"),

            // Hechos del tiburón (dará falso, faltará "Piel_Rugosa")
            Map.of("Aletas", "true"),
            Map.of("Depredador", "true"),
            Map.of("Nada", "true"),

            // Hechos de la ballena (dará falso, faltará "Canto_Submarino")
            Map.of("Aletas", "true"),
            Map.of("Respira_Aire", "true"),

            // Hechos de la medusa (dará falso, faltará "Transparente")
            Map.of("Aletas", "false"),
            Map.of("Nada", "true")
        );

        for (Map<String, String> fact : facts) {
            for (Map.Entry<String, String> entry : fact.entrySet()) {
                String attribute = entry.getKey();
                String value = entry.getValue();

                if (condition.get("attribute").equals(attribute) && condition.get("value").equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void evaluateResults() {
        if (conditionsResults.stream().allMatch(result -> result)) {
            System.out.println("El objetivo es definitivamente Verdadero");
        } else {
            System.out.println("El objetivo es definitivamente Falso");
        }
    }

    public static void backwardChaining(Node hypothesis) {
        for (Object condition : hypothesis.getConditions()) {
            if (condition instanceof Node) {
                Node nodeCondition = (Node) condition;
                System.out.println("Hipótesis: " + nodeCondition.getAttribute() + " => " + nodeCondition.getValue());
                backwardChaining(nodeCondition);
            } else {
                Map<String, String> conditionMap = (Map<String, String>) condition;
                System.out.println("Condición: " + conditionMap.get("attribute") + " => " + conditionMap.get("value"));
                boolean result = validateRule(conditionMap);
                conditionsResults.add(result);
                System.out.println("Condición en base de hechos: " + result + "\n");
            }
        }
    }

     public static void loadExample() {
        // Hipótesis del pingüino
        allHypothesis.put(1, new Node("Pinguino", "true", List.of(
            Map.of("attribute", "Plumas", "value", "true"),
            Map.of("attribute", "Vuela", "value", "false"),
            Map.of("attribute", "Nada", "value", "true"),
            Map.of("attribute", "Color", "value", "blanco_negro")
        )));

        // Hipótesis de la gallina
        allHypothesis.put(2, new Node("Gallina", "true", List.of(
            Map.of("attribute", "Plumas", "value", "true"),
            Map.of("attribute", "Vuela", "value", "false"),
            Map.of("attribute", "Nada", "value", "false"),
            Map.of("attribute", "Color", "value", "blanco_marron")
        )));

        // Hipótesis del canario
        allHypothesis.put(3, new Node("Canario", "true", List.of(
            Map.of("attribute", "Plumas", "value", "true"),
            Map.of("attribute", "Vuela", "value", "true"),
            Map.of("attribute", "Color", "value", "amarillo"),
            Map.of("attribute", "Canta", "value", "true")  // Esta condición será falsa en la base de hechos
        )));

        // Hipótesis del avestruz
        allHypothesis.put(4, new Node("Avestruz", "true", List.of(
            Map.of("attribute", "Plumas", "value", "true"),
            Map.of("attribute", "Vuela", "value", "false"),
            Map.of("attribute", "Patas_Largas", "value", "true")
        )));

        // Hipótesis del flamenco
        allHypothesis.put(5, new Node("Flamenco", "true", List.of(
            Map.of("attribute", "Plumas", "value", "true"),
            Map.of("attribute", "Vuela", "value", "true"),
            Map.of("attribute", "Patas_Largas", "value", "true"),
            Map.of("attribute", "Color", "value", "rosado")  // Esta condición será falsa en la base de hechos
        )));

        // Hipótesis del pez payaso
        allHypothesis.put(6, new Node("Pez_Payaso", "true", List.of(
            Map.of("attribute", "Aletas", "value", "true"),
            Map.of("attribute", "Color", "value", "naranja_blanco"),
            Map.of("attribute", "Nada", "value", "true")
        )));

        // Hipótesis del delfín
        allHypothesis.put(7, new Node("Delfin", "true", List.of(
            Map.of("attribute", "Aletas", "value", "true"),
            Map.of("attribute", "Respira_Aire", "value", "true"),
            Map.of("attribute", "Inteligencia", "value", "alta")
        )));

        // Hipótesis del tiburón (dará falso, faltará "Piel_Rugosa")
        allHypothesis.put(8, new Node("Tiburon", "true", List.of(
            Map.of("attribute", "Aletas", "value", "true"),
            Map.of("attribute", "Depredador", "value", "true"),
            Map.of("attribute", "Piel_Rugosa", "value", "true")
        )));

        // Hipótesis de la ballena (dará falso, faltará "Canto_Submarino")
        allHypothesis.put(9, new Node("Ballena", "true", List.of(
            Map.of("attribute", "Aletas", "value", "true"),
            Map.of("attribute", "Respira_Aire", "value", "true"),
            Map.of("attribute", "Canto_Submarino", "value", "true")
        )));

        // Hipótesis de la medusa (dará falso, faltará "Transparente")
        allHypothesis.put(10, new Node("Medusa", "true", List.of(
            Map.of("attribute", "Aletas", "value", "false"),
            Map.of("attribute", "Nada", "value", "true"),
            Map.of("attribute", "Transparente", "value", "true")
        )));
    }

    public static void menuBackwardChaining() {
        System.out.println("\n\n--- ENCADENAMIENTO HACIA ATRÁS ---");
        for (Map.Entry<Integer, Node> entry : allHypothesis.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getAttribute() + " => " + entry.getValue().getValue());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Escoja la hipótesis: ");
        int num = scanner.nextInt();

        backwardChaining(allHypothesis.get(num));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\nMENU DE OPCIONES");
            System.out.println("1. Encadenamiento hacia atrás");
            System.out.println("2. Cargar Base de Conocimientos");
            System.out.println("9. Salir");

            System.out.print("Ingresa una opción: ");
            int option = scanner.nextInt();

            if (option == 1) {
                menuBackwardChaining();
                evaluateResults();
                conditionsResults.clear();  // Limpiar resultados para la siguiente evaluación
            } else if (option == 2) {
                loadExample();
            } else if (option == 9) {
                System.out.println("\nSaliendo...");
                break;
            }
        }
    }
}
