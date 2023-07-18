import java.util.*;

public class Calculator {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите пример: ");
        String phrase = in.nextLine().toUpperCase();
        calc(phrase);
    }
    public static void calc(String phrase) throws Exception {

        String[] chisla = phrase.split("[+\\-*/]");

        Map<String, Integer> converter = new HashMap<>();
        converter.put("I", 1);
        converter.put("II", 2);
        converter.put("III", 3);
        converter.put("IV", 4);
        converter.put("V", 5);
        converter.put("VI", 6);
        converter.put("VII", 7);
        converter.put("VIII", 8);
        converter.put("IX", 9);
        converter.put("X", 10);

        if (!phrase.matches(".*[+\\-*/].*")) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (chisla.length != 2) {
            throw new Exception("Количество операндов должно быть равно двум");
        }
        for (int u = 0; u < chisla.length; u++) {
            if (!chisla[u].matches("[1-9]|10") && !converter.containsKey(chisla[u])) {
                throw new Exception("Введенные числа не попадают в диапазон (1-10)");
            }
        }
        if (chisla[0].matches("[1-9]|10") && chisla[1].matches("[1-9]|10")) Arab(phrase, chisla);
        else if (converter.containsKey(chisla[0]) && converter.containsKey(chisla[1])) Rim(phrase, chisla);
        else throw new Exception("Используются одновременно разные системы счисления");
    }
    public static void Arab(String phrase,String[] chisla){
        System.out.println("Результат = " + PM(phrase, chisla));
    }

    private static int PM(String phrase,String[] chisla) {
        int x = Integer.parseInt(chisla[0]);
        int y = Integer.parseInt(chisla[1]);
        int result;
        if (phrase.contains("+")) {
            result = x + y;
            return result;
        } else if (phrase.contains("-")) {
            result = x - y;
            return result;
        } else if (phrase.contains("*")) {
            result = x * y;
            return result;
        } else if (phrase.contains("/")) {
            result = x / y;
            return result;
        }
        return 0;
    }

    public static void Rim(String phrase,String[] chisla)throws Exception {
        String[] arr = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int result;
            chisla[0]= String.valueOf(Arrays.asList(arr).indexOf(chisla[0])+1);
            chisla[1]= String.valueOf(Arrays.asList(arr).indexOf(chisla[1])+1);
        result = PM(phrase, chisla);
        if (result<=0)
            throw new Exception("В римской системе нет отрицательных чисел");
        else System.out.println("Результат = " + Rimconv(result));
    }

    public static String Rimconv(int result){
        String[] ed = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] des = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] sot = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String s,d,e;

        s = sot[result / 100 % 10];
        d = des[result / 10 % 10];
        e = ed[result % 10];
        return s+d+e;
    }
}