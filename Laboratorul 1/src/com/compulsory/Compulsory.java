package com.compulsory;

public class Compulsory {
    private static int sumOfDigits(int n) {
        while (true) {
            int sum = 0;
            while (n > 0)
            {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
            if (n < 10)
                return n;
        }
    }
    public static void solve() {
        // first step
        System.out.println("Hello World!");
        // second step
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        // third step
        int n = (int) (Math.random() * 1_000_000);
        // fourth step
        n = (n * 3 + Integer.parseInt("10101", 2) + Integer.parseInt("FF", 16)) * 6;
        // fifth step
        n = sumOfDigits(n);
        // sixth step
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
}
