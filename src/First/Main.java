/*2. Читаючи з файлу рядки, ті з них, що представляють цілі числа (містять лише цифри),
просумувати і вивести на консоль (суму і усі доданки),
у решті рядків змінити порядок слів на протилежний,
 та вивести у файл, заданий користувачем.*/
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// Запитуємо в користувача назву файлу
            System.out.print("Введіть назву файлу: ");
            String fileName = br.readLine();

// Відкриваємо файл для читання
            BufferedReader fileReader = null;
            boolean fileExists = false;
            while (!fileExists) {
                try {
                    fileReader = new BufferedReader(new FileReader(fileName));
                    fileExists = true;
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено. Спробуйте знову.");
                    System.out.print("Введіть назву файлу: ");
                    fileName = br.readLine();
                }
            }

            String line;
            int sum = 0;
            while ((line = fileReader.readLine()) != null) {
                // Перевіряємо, чи є ціле число
                if (line.matches("[\\d\\s-]*$")) {
                    String[] numbersStr = line.split("\\s+");
                    int[] numbers = new int[numbersStr.length];
                    for (int i = 0; i < numbersStr.length; i++) {
                        try {
                            if (numbersStr[i].matches("^-?\\d+$")) {
                                numbers[i] = Integer.parseInt(numbersStr[i]);
                                System.out.println(numbers[i]);
                                sum += numbers[i];
                            }
                        }  catch (NumberFormatException e) {
                            // ignore non-numeric values
                        }
                    }

                } else {
                    // Якщо рядок не є цілим числом, змінюємо порядок слів на протилежний
                    String[] words = line.split("\\s+");
                    for (int i = words.length - 1; i >= 0; i--) {
                        // System.out.print(words[i] + " ");
                    }
                    //  System.out.println();
                }
            }

// Виводимо суму цілих чисел
            System.out.println("Сума цілих чисел: " + sum);

// Запитуємо в користувача назву файлу для запису
            System.out.print("Введіть назву файлу для запису: ");
            String outputFileName = br.readLine();

// Відкриваємо файл для запису
            BufferedWriter fileWriter = null;
            boolean fileCreated = false;
            while (!fileCreated) {
                try {
                    fileWriter = new BufferedWriter(new FileWriter(outputFileName));
                    fileCreated = true;
                } catch (IOException e) {
                    System.out.println("Не вдалося створити файл. Спробуйте знову.");
                    System.out.print("Введіть назву файлу для запису: ");
                    outputFileName = br.readLine();
                }
            }

            // Перевіряємо файл знову
            fileReader = new BufferedReader(new FileReader(fileName));
            while ((line = fileReader.readLine()) != null) {
                if (!line.matches("[\\d\\s-]*$")) {
                    String[] words = line.split("\s+");
                    for (int i = words.length - 1; i >= 0; i--) {
                        fileWriter.write(words[i] + " ");
                    }
                    fileWriter.newLine();
                }
            }

            // Закриваємо файли
            fileReader.close();
            fileWriter.close();
            System.out.println("Запис завершено!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}