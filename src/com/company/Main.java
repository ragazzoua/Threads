package com.company;

import static com.company.ThreadColor.ANSI_GREEN;
import static com.company.ThreadColor.ANSI_PURPLE;
import static com.company.ThreadColor.ANSI_RED;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");
        anotherThread.start();

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN  + "Hello from the anonymous class thread");
            }
        }.start();

        Thread myRunnablethread = new Thread(new MyRunnable(){
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementatiom of run");
                try {
                    anotherThread.join();
                    System.out.println(ANSI_RED + "Another thread terminated, or time'd out, so i'm running again");
                }catch (InterruptedException e){
                    System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
                }
            }
        });

        myRunnablethread.start();

        System.out.println(ANSI_PURPLE + "Hello again from main thread.");
    }
}
