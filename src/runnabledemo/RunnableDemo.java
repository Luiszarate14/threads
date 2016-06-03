/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runnabledemo;

/**
 *
 * @author luisza
 */
public class RunnableDemo implements Runnable {

   private Thread t;
   private String threadName;    

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Lock lock = new Lock();
      ControlValue control = new ControlValue(10, 0);
      ShareVarLock t1 = new ShareVarLock("Thread-1", control, lock);
      ShareVarLock t2 = new ShareVarLock("Thread-2", control, lock);
      
      t1.start();
      t2.start();      
      
      /*
      ShareVar t1 = new ShareVar("Thread-1", control);
      ShareVar t2 = new ShareVar("Thread-2", control);
      
      t1.start();
      t2.start();

      */
      
      
      /*
      RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Thread-2");
      R2.start();  
    */
    }
   
   
    public RunnableDemo(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " +  threadName );
    }
    

    @Override
    public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
     } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
     }
     System.out.println("Thread " +  threadName + " exiting.");       


    }
    
   public void start ()
   {
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
    
}
