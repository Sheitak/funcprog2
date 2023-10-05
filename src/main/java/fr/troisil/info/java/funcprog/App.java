package fr.troisil.info.java.funcprog;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class App {
    public static void main( String[] args ) throws Exception {
        try {
            // Indique le chemin du fichier.
            Path path = Paths.get("src/main/resources/personnes.csv");

            // Lire les lignes du fichier Stream + Ignorer première ligne.
            Stream<String> lines = Files.lines(path).skip(1);

            // Convertir la chaine en objet Personne.
            List<Personne> personnes = lines.map(
                    line -> {
                        // Découper la chaîne de caractères.
                        String[] elements = line.split(",");
                        String name = elements[0];
                        int age = Integer.valueOf(elements[1]);

                        return new Personne(name, age);
                    })
                    .collect(Collectors.toList());

            // 8.2 - Trier la liste des personnes par âge croissant.
            List<Personne> personnesASC = personnes.stream()
                    .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                    .collect(Collectors.toList());

            // 8.3 - Trier la liste des personnes par âge croissant.
            List<Personne> personnesComparator = personnes.stream()
                    .sorted(Comparator.comparingInt(Personne::getAge))
                    .collect(Collectors.toList());

            // 8.4 - Trier par âge décroissant, puis par prénom croissant
            Comparator<Personne> comparator = Comparator
                    .comparingInt(Personne::getAge)
                    .reversed()
                    .thenComparing(Personne::getName);

            List<Personne> personnesDESC = personnes.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());

            // Affichez les personnes.
            personnes.forEach(System.out::println);
            System.out.println("-----------");

            personnesASC.forEach(System.out::println);
            System.out.println("-----------");

            personnesComparator.forEach(System.out::println);
            System.out.println("-----------");

            personnesDESC.forEach(System.out::println);
            System.out.println("-----------");
        } catch(Exception ex) {
            throw new Exception();
        }
    }
}
