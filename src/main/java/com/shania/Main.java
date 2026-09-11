package com.shania;
import com.shania.ui.ProductMenuUI;

public class Main {
    public static void main(String[] args) {
        ProductMenuUI menuUI = new ProductMenuUI();
        menuUI.startMenu();
    }
}
//public class Main {
//    public static void main(String[] args) {
//        try {
//            // 3. The exception is finally caught here in the parent caller
//            methodOne();
//        } catch (Exception e) {
//            System.out.println("Caught in main: " + e);//Caught in main: java.lang.Exception: Error from methodTwo
//            System.out.println("Caught in main: " + e.getMessage());//Caught in main: Error from methodTwo
//            System.out.println("Caught in main: " + e.getCause());//Caught in main: null
//        }
//    }
//    public static void methodOne() throws Exception {
//        // 2. This method does not catch it, so it passes it up to main
//        methodTwo();
//    }
//    public static void methodTwo() throws Exception {
//        // 1. Exception starts here
//        throw new Exception("Error from methodTwo");
//    }
//}
