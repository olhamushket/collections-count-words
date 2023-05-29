//package com.epam.rd.autotasks;
//
//
//import java.util.*;
//
//public class Words {
//
//    public String countWords(List<String> lines) {
//        Map<String, Integer> otchet = new HashMap<>();
//        Map<String, Integer> otchet2 = new HashMap<>();
//
//        for (String str : lines) {
//            List<String> slova = new LinkedList<>(Arrays.asList(str.split("[ .,‘’(“—/:?!”;*)'\"-]|\\s+]")));
//
//
//            for (String str1 : slova) {
//                String str2 =str1.toLowerCase();
//                if (str2.length() >= 4) {
//                    otchet.put(str2, otchet.getOrDefault(str2, 0)+1);
//                }
//            }
//        }
//
//        for (Map.Entry<String, Integer> entry : otchet.entrySet()) {
//            if (entry.getValue() >= 10) {
//                otchet2.put(entry.getKey(), entry.getValue());
//            }
//        }
//
//        List<Map.Entry<String,Integer>> entries = new ArrayList<>(otchet2.entrySet());
////        entries.sort(new Comparator<>() {
////            @Override
////            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
////                int countComparison = o2.getValue().compareTo(o1.getValue());
////                if (countComparison != 0) {
////                    return countComparison;
////                }
////                return o1.getKey().compareTo(o2.getKey());
////            }
////        });
//        entries.sort((Map.Entry.<String, Integer>comparingByValue().reversed()
//                .thenComparing(Map.Entry.comparingByKey())));
//        StringBuilder result = new StringBuilder();
//        for (Map.Entry<String, Integer> entry : entries) {
//                result.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
//            }
//        result.delete(result.length() - 1, result.length());
//        return result.toString();
//        }
//}
//
package com.epam.rd.autotasks;

import java.util.*;

public class Words {
    Map<String, Integer> map = new HashMap<>();

    public String countWords(List<String> lines) {
        for (String line : lines) {
            String[] words = line.split("[ .,‘’(“—/:?!”;*)'\"-]|\\s+]");

            for (String word : words) {
                word = word.toLowerCase();
                if (word.length() > 3 && map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else if (word.length() > 3) {
                    map.put(word, 1);
                }
            }
        }

        Map<String, Integer> mapCandidates = new TreeMap<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 9) {
                mapCandidates.put(entry.getKey(), entry.getValue());
            }
        }

        Map<String, Integer> sortedMap = sortByValue(mapCandidates);
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        sb.delete(sb.length() - 1, sb.length());
        return String.valueOf(sb);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {
        List<Map.Entry<K, V>> list = new LinkedList<>(unsortMap.entrySet());
        list.sort(new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                Integer x1 = (Integer) o1.getValue();
                Integer x2 = (Integer) o2.getValue();
                int compareByValue = x2.compareTo(x1);

                if (compareByValue != 0) return compareByValue;

                String s1 = (String) o1.getKey();
                String s2 = (String) o2.getKey();
                return s1.compareTo(s2);
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}