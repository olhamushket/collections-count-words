package com.epam.rd.autotasks;


import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> otchet = new HashMap<>();
        Map<String, Integer> otchet2 = new HashMap<>();

        for (String str : lines) {
            List<String> slova = new LinkedList<>(Arrays.asList(str.split("[ .,‘’(“—/:?!”;*)'\"-]|\\s+]")));


            for (String str1 : slova) {
                String str2 =str1.toLowerCase();
                if (str2.length() >= 4) {
                    otchet.put(str2, otchet.getOrDefault(str2, 0)+1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : otchet.entrySet()) {
            if (entry.getValue() >= 10) {
                otchet2.put(entry.getKey(), entry.getValue());
            }
        }

        List<Map.Entry<String,Integer>> entries = new ArrayList<>(otchet2.entrySet());
//        entries.sort(new Comparator<>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                int countComparison = o2.getValue().compareTo(o1.getValue());
//                if (countComparison != 0) {
//                    return countComparison;
//                }
//                return o1.getKey().compareTo(o2.getKey());
//            }
//        });
        entries.sort((Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey())));
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
                result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
            }
        result.delete(result.length() - 1, result.length());
        return result.toString();
        }
}

