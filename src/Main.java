import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
static String method = " ";

    public static void printEncodeDecode() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Для расшифровки текста введите 'decode'.\nДля шифрования текста 'encode': ");
        method = scanner.nextLine(); // пишу decode или encode

    }
    private static void decryptedEncrypted() {
        int key = 5;

        String currentPath = System.getProperty("user.dir") + "/src/";
        FileHandler encryptedFile = new FileHandler(currentPath + "encrypted_text.txt");
        FileHandler decryptedFile = new FileHandler(currentPath + "decrypted_text.txt");

        List<String> outputLines = new ArrayList<>();

        String outputPath = "";


        if (method.equals("encode"))
        {
            List<String> linesToProcess = decryptedFile.readLines();
            outputPath = currentPath + "encrypted_text.txt";
            for (String line : linesToProcess)
            {
                outputLines.add(CaesarCipher.encode(line, key));
            }
        }
        if (method.equals("decode"))
        {
            List<String> linesToProcess = encryptedFile.readLines();
            outputPath = currentPath + "decrypted_text.txt";
            for (String line : linesToProcess)
            {
                outputLines.add(CaesarCipher.decode(line, key));
            }
        }

        FileHandler outputFile = new FileHandler(outputPath);
        outputFile.writeLines(outputLines);
        System.out.println("Succsesfull");
    }
    public static void main(String[] args) {

        printEncodeDecode();
        decryptedEncrypted();

    }
}
