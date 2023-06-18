import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

class CaesarCipher
{
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? ";
    private static final int ALPHABET_SIZE = 33;


    private static Map<Character, Character> makeTrans(String str1, String str2)
    {
        Map<Character, Character> dictionary = new HashMap<>();
        for (int i = 0; i < str1.length(); i++)
        {
            char key = str1.charAt(i);
            char value = str2.charAt(i);
            dictionary.put(key, value);
        }
        return dictionary;
    }

    private static String processOnString(String string, Map<Character, Character> dict)
    {
        String output = "";
        for (int i = 0; i < string.length(); i++)
        {
            char tmpChar = string.charAt(i);
            if (dict.containsKey(tmpChar)) {
                output = output + dict.get(tmpChar);
            } else {
                output = output + tmpChar;
            }
        }
        return output;
    }

    public static String encode(String string, int key)
    {
        if (key >= ALPHABET_SIZE)
        {
            key = key % ALPHABET_SIZE;
        }
        return processOnString(string, makeTrans(ALPHABET, ALPHABET.substring(key) + ALPHABET.substring(0, key)));
    }

    public static String decode(String string, int key)
    {
        if (key >= ALPHABET_SIZE)
        {
            key = key % ALPHABET_SIZE;
        }
        return processOnString(string, makeTrans(ALPHABET.substring(key) + ALPHABET.substring(0, key), ALPHABET));
    }

    public static void bruteForce(String encodedString, String russianWords, Double threshold)
    {

        Set<String> encodedUniqueWords = StringUtils.getUniqueWords(encodedString);
        double encodedUniqueWordsSize = encodedUniqueWords.size();

        Set<String> rWords = StringUtils.getUniqueWords(russianWords);

        for (int i = 0; i < CaesarCipher.ALPHABET_SIZE; i++)
        {
            Set<String> tmpSet = new HashSet<>();
            for (String encodedWord : encodedUniqueWords)
            {
                tmpSet.add(decode(encodedWord, i).toLowerCase());
            }

            // Создаём новый Set, содержащий слова, которые есть и в расшифрованной строке, и в словаре
            Set<String> intersection = new HashSet<>(tmpSet);
            intersection.retainAll(rWords);
            double matchCount = intersection.size();

            double matchPercentage = Math.round(matchCount / encodedUniqueWordsSize * 10000d) / 100d;
            if (matchPercentage > threshold)
            {
                System.out.println("\nKey: " + i + "\nMatch percentage: " + matchPercentage + "\nPossible text: " + decode(encodedString, i));
            }
        }
    }
}