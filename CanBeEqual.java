import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CanBeEqual {

    static Set<String> characters = new HashSet<String>();
    static Set<String> sortNumbers = new HashSet<String>();
    static ArrayList<StringBuilder> withoutBrackets = new ArrayList<StringBuilder>();
    static ArrayList<StringBuilder> withBrackets = new ArrayList<StringBuilder>();

    public static void main(String[] args){
        int[] nums = new int[] {1,1,2,7};
        canBeEqualTo24(nums);
    }

    public static boolean canBeEqualTo24(int[] nums) {

        boolean b=false;
        long result=0;
        final char o = '(';
        final char c = ')';
        String numbers = "";
        for (int n : nums)
            numbers += String.valueOf(n);
        permutation("", numbers);
        permutationCharacters();

        for (String number : sortNumbers) {
            for (String symbol : characters) {
                withoutBrackets.add(new StringBuilder().append(number, 0, 1)
                        .append(symbol, 0, 1)
                        .append(number, 1, 2)
                        .append(symbol, 1, 2)
                        .append(number, 2, 3)
                        .append(symbol, 2, 3)
                        .append(number, 3, 4)
                );
            }
        }

        StringBuilder buff;
        for (StringBuilder strBld : withoutBrackets) {
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(7, c).insert(4, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(3, c).insert(0, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(5, c).insert(0, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(7, c).insert(2, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(7, c).insert(4, o).insert(3, c).insert(0, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(5, c).insert(2, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(5, c).insert(3, c).insert(0, o).insert(0, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(7, c).insert(7, c).insert(4, o).insert(2, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(5, c).insert(5, c).insert(2, o).insert(0, o));
            buff = new StringBuilder(strBld);
            withBrackets.add(buff.insert(7, c).insert(5, c).insert(2, o).insert(2, o));
        }

        for (StringBuilder stringBuilder : withBrackets) {
            try {
                Object obj = new ScriptEngineManager().getEngineByName("JavaScript").eval(stringBuilder.toString());
                if(obj instanceof  Integer){
                    result = ((Integer)obj).longValue();
                }
                if (obj instanceof Double){
                    result = Math.round(((Double)obj).doubleValue());
                }
                if (result==24) {
                    System.out.println(stringBuilder.toString()+" = 24");
                    b=true;
                    break;
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(b);
        return b;
    }

    private static void permutation(String prefix, String str) {

        int n = str.length();
        if (n == 0) {
            sortNumbers.add(prefix);
        } else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }


    private static void permutationCharacters() {
        final String operators = "+-*/";
        String s;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    s= String.valueOf(operators.charAt(i))+operators.charAt(j)+operators.charAt(k);
                        characters.add(s);
                }
            }
        }
    }

}