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
public class ShareVarLock extends Thread {

    private Thread t;
    private String threadName;
    private ControlValue control;
    private Lock lock;

    public ShareVarLock(String threadName, ControlValue control, Lock lock) {
        this.threadName = threadName;
        this.control = control;
        this.lock = lock;
    }

    public void run() {
        System.out.println("Running " + threadName + " with " + control.value);
        while (control.position > 0) {
           
            try {
                lock.lock();
                System.out.println("(" + threadName + "): " + control.position + " = " + control.value);
                control.value++;
                control.position--;
                lock.unlock();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ShareVarLock.class.getName()).log(Level.SEVERE, null, ex);
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
