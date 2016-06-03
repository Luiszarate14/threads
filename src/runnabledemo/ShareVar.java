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
public class ShareVar  extends Thread{
   private Thread t;
   private String threadName;
   private ControlValue control;

   public ShareVar(String threadName, ControlValue control) {
        this.threadName = threadName;
        this.control = control;
    }

   public void run() {
       System.out.println("Running "+threadName+" with "+control.value);
       while(control.position > 0 ){
           System.out.println("("+threadName+"): "+control.position+" = "+ control.value);
           control.value++;
           control.position--;
           try {
               Thread.sleep(50);
           } catch (InterruptedException ex) {
               Logger.getLogger(ShareVar.class.getName()).log(Level.SEVERE, null, ex);
           }
       }

   }
   
   public void start (){
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
    
    
}
