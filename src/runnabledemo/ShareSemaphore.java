/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnabledemo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisza
 */
public class ShareSemaphore extends Thread {

    private Thread t;
    private String threadName;
    private ControlValue control;
    private Semaphore semaphore;
    private Lock lock;

    public ShareSemaphore(String threadName, ControlValue control,
            Lock lock, Semaphore semaphore) {
        this.threadName = threadName;
        this.control = control;
        this.semaphore = semaphore;
        this.lock = lock;
    }

    public void run() {
        System.out.println("Running " + threadName + " with " + control.value);
        while (control.position > 0) {
           
            try {
                for(int x=0; x<4; x++){
                semaphore.take();
                    lock.lock();
                        System.out.println("(" + threadName + "): " + control.position + " = " + control.value);
                        control.value++;
                        control.position--;
                    lock.unlock();
                
                }
                //yield();
                for(int x=0; x<4; x++) semaphore.release();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ShareSemaphore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

}
