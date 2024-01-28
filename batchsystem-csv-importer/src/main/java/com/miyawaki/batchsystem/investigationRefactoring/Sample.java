package com.miyawaki.batchsystem.investigationRefactoring;

public class Sample {
    public static void main(String[] args) {
        // String result = addString(100);
        String result = addString(50);
        System.out.println(result);
    }
    public static String addString(int num){
        StringBuilder result= new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(""+i);
        }
        return result.toString();
    }
    public static String addStream(int num){
        // return java.util.stream.IntStream.range(0, num).mapToObj(String::valueOf).reduce("", (a, b) -> a + b);
        return java.util.stream.IntStream.range(0, num).mapToObj(String::valueOf).reduce("", String::concat);
    }
}
