/*Написати додаток, що виконує порядковий запис символів з консолі в файл.
Закінчення введення - Ctrl + Z.
 Рядок Null у вихідний потік не писати.*/
import java.io.*;

public class ConsoleToFile {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileWriter writer = new FileWriter("output.txt");
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("\u0004")) { // Ctrl + D
                    break;
                }
                if ( line.length() > 0) { // перевірка чи довжина рядка більше 0
                    writer.write(line);
                    writer.write(System.lineSeparator()); // додати перенос рядка
                }
            }
            writer.close();
            reader.close();
            System.out.println("Дані успішно записано в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}