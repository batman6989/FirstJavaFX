package com.example.uianalzyer;


import java.util.*;
import java.io.File;
import java.lang.reflect.Array;
import java.io.FileNotFoundException;
public class Fileconverter {
    public static ArrayList<String> array;
    public static List<String> sort(List<String> clean) {
        Collections.sort(clean, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o2) - extractInt(o1);
            }
            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
        return clean;
    }
    public static void textT(File poem, int resultsRange) throws FileNotFoundException {
        Scanner reader = new Scanner(poem);
        ArrayList<String> list = new ArrayList<String>();
        int line = 0;
        while (reader.hasNextLine()) {
            String reading = reader.nextLine();
            reading = reading.toLowerCase();
            if (line > 77 && line < 243) {
                reading = reading.replaceAll("\\<[^>]*>", "");
                reading = reading.replaceAll("&mdash;", " ");
                reading = reading.replaceAll("[^a-zA-Z0-9\\s+]", "");
                String broken[] = reading.split(" ");
                List<String> splitList = new ArrayList<String>();

                splitList = Arrays.asList(broken);

            for (String s : splitList) {
                    if (!s.isEmpty()) {
                        list.add(s);
                    }
                }
            }
            line++;
        }
        Collections.sort(list);
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(list);
        ArrayList<String> reference = new ArrayList<String>(hashSet);
        List<String> frequencyCount = new ArrayList<>();
        for (int i = 0; i < reference.size(); i++) {
            Integer num = Collections.frequency(list, reference.get(i));
            String numCount = num.toString();
            frequencyCount.add("\"" + reference.get(i) + "\"" + " " + numCount);
        }
        sort(frequencyCount);
        if (resultsRange > frequencyCount.size()) {
            resultsRange = frequencyCount.size();
        }
    if (resultsRange <= 0) {
            resultsRange = 1;
        }
        Integer counter = 1;
        System.out.println();
        System.out.println("Top " + resultsRange + " Results found in the html document");
        System.out.println();

        ArrayList<String> copy = new ArrayList<String>();
        for (int i = 0; i < resultsRange; i++) {
            String counterString = counter.toString();
            System.out.println(counterString + ". " + frequencyCount.get(i));
            copy.add(counterString + ". " + frequencyCount.get(i));
            counter++;
        }
        array = copy;
        reader.close();
    }
}
