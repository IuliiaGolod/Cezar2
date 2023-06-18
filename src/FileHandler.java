import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


class FileHandler
{
    private final String filePath;
    public FileHandler(String filePath)
    {
        this.filePath = filePath;
    }

    public List<String> readLines()
    {
        List<String> output = new ArrayList<>();
        try
        {
            File fo = new File(this.filePath);
            Scanner reader = new Scanner(fo);
            while (reader.hasNextLine())
            {
                output.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public void writeLines(List<String> lines)
    {
        try
        {
            FileWriter writer = new FileWriter(this.filePath);
            for (String line: lines)
            {
                writer.write(line+System.getProperty("line.separator"));
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}