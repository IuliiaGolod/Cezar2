import java.util.HashSet;
import java.util.Set;
import java.util.*;


class StringUtils {
    static Set<String> getUniqueWords(String string)
    {
        String[] words = string.toLowerCase().split("\\s+");
        return new HashSet<>(Arrays.asList(words));
    }
}