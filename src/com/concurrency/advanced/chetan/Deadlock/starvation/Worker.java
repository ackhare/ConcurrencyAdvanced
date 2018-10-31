package Deadlock.starvation;

import java.io.*;

/**
 * Worker.java
 * This class is used to demonstrate starvation situation.
 * @author www.codejava.net
 */
public class Worker {
 
    public synchronized void work() {
        String name = Thread.currentThread().getName();
        String fileName = name + ".txt";
 
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        ) {
            writer.write("Thread " + name + " wrote this mesasge");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
 
        while (true) {
        	//To Avoid starvation
//        	try {
//                wait(1000);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
         
            System.out.println(name + " is working");
        }
    }
}
