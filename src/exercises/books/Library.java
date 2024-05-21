package exercises.books;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
// Quantità di libri da inserire
        int quantity = 0;

        //Creo resources
        File dir = new File("./resources");
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory 'resources' creata con successo.");
            } else {
                System.out.println("Impossibile creare la directory 'resources'.");
                return;
            }
        }

        //Chiedo all'utente il numero di libri che vuole inserire
        while (quantity <= 0) {
            try {
                System.out.println("Quanti libri vuoi inserire?");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserisci una quantità valida");
            } catch (IllegalArgumentException e) {
                System.out.println("Inserisci una quantità positiva");
            }
        }
//Creo l'array richiesto
        Book[] books = new Book[quantity];
//Chiedo le informazioni per ogni libro
        for (int i = 0; i < quantity; i++) {
            String title = "";
            String author = "";
            String editor = "";
            int numberOfPages = 0;

                int NumberofBooks = i + 1;
                System.out.println("INSERIAMO IL LIBRO N°" + NumberofBooks);
                System.out.println("-----------------");
                System.out.println("Inserisci il titolo del libro:");
                title = scanner.nextLine();
                System.out.println("Inserisci l'autore del libro:");
                author = scanner.nextLine();
                System.out.println("Inserisci l'editore del libro:");
                editor = scanner.nextLine();


                boolean validNumberOfPages = false;
                while (!validNumberOfPages) {
                    try {
                        System.out.println("Inserisci il numero di pagine del libro:");
                        numberOfPages = Integer.parseInt(scanner.nextLine());
                        if (numberOfPages <= 0) {
                            throw new IllegalArgumentException("Il numero di pagine deve essere maggiore di zero");
                        }
                        validNumberOfPages = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Inserisci un numero di pagine valido");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                books[i] = new Book(title, numberOfPages, author, editor);
            }
//Scrivo il libro nel file
            File file = new File("./resources/books.txt");
            FileWriter fileWriter = null;

            try {
                fileWriter = new FileWriter(file);
                fileWriter.write("I libri che hai inserito sono:\n");
                for (int i = 0; i < books.length; i++) {
                    fileWriter.write(books[i].bookInfo() + "\n");
                }

            } catch (IOException e) {
                System.out.println("Impossibile accedere al file");
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        System.out.println("Errore durante la chiusura del fileWriter");
                    }
                }
            }

            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Impossibile leggere il file");
            }

            scanner.close();
        }
    }
