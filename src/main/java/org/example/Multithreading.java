package org.example;


class ThreadTwo implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread is runnning");
        try {
            System.out.println(Thread.currentThread().getName());

            Thread.sleep(3000);


        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("END");
    }
}


class ThreadOne extends Thread{
    String str;
    static String vowels = "AEIOUaeiou";
    public ThreadOne(String s){
        this.str = s;
    }
    public void run(){
        int n = 0;
        for(char c: str.toCharArray() ){
            if(vowels.contains(String.valueOf(c))){
                n++;
            }
        }
        System.out.println(n);
    }


}

public class Multithreading {
    public static int n = 0;
    public static void main(String[] args){
        String[] arr = {"java","python","sql","mongodb","react","angular","c++","javascript","html","css"};
        for(String str: arr){
            ThreadOne threadobj = new ThreadOne(str);
            threadobj.start();
        }


    }

    public synchronized int getValue(){
        return ++n;
    }
}
