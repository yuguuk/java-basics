package refresher.basics;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {
    private String parameter;

    public MyThread(String parameter) {
        this.parameter = parameter;
    }

    public void run() {
        while (!"exit".equals(parameter)) {
            System.out.println((isDaemon() ? "daemon" : " user") + " thread " + this.getName() + "(id=" + this.getId()
                    + ") parameter: " + parameter);
            pauseOnSecond();
        }

        System.out.println((isDaemon() ? "daemon" : " user") + " thread " + this.getName() + "(id=" + this.getId()
                + ") parameter:" + parameter);
    }

    private static void pauseOnSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public static void main(String[] args) {
        MyThread thr1 = new MyThread("One");
        thr1.start();
        MyThread thr2 = new MyThread("Two");
        thr2.setDaemon(true);
        thr2.start();
        pauseOnSecond();
        thr1.setParameter("exit");
        pauseOnSecond();
        System.out.println("Main thread exists");
    }

}

class MyRunnable implements Runnable{

    private String parameter, name;

    public MyRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while(!"exit".equals(parameter)){
            System.out.println("thread " + this.name + ", parameter: " + parameter);
            pauseOneSecond();
        }

        System.out.println("thread " + this.name + ", parameter: " + parameter);
    }

    public void setParameter(String parameter){
        this.parameter = parameter;
    }

    private static void pauseOneSecond(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String ... args){
        MyRunnable myRunnable1 = new MyRunnable("Runnable One");
        MyRunnable myRunnable2 = new MyRunnable("Runnable Two");

        Thread thr1 = new Thread(myRunnable1);
        thr1.start();
        Thread thr2 = new Thread(myRunnable2);
        thr2.setDaemon(true);
        thr2.start();
        pauseOneSecond();
        myRunnable1.setParameter("exit");
        pauseOneSecond();
        System.out.println("Main thread exists");
    }

}

class MyRunnable2 implements Runnable{
    private String name;

    public MyRunnable2(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try{
            while(true){
                System.out.println(this.name + " is working");
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e){
            System.out.println(this.name + " was interrupted\n"
                    + this.name + " Thread.currentThread().isInterrupted()="
                    + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String ... args){
        ExecutorService pool = Executors.newCachedThreadPool();
        String[] names = {"One","Two","Three"};
        for (String s : names) {
            pool.execute(new MyRunnable2(s));
        }

        System.out.println("Before shutdown: isShutdowwn()=" + pool.isShutdown()
        + ", isTerminated()=" + pool.isTerminated());

        try{
            long timeOut = 100;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            System.out.println("Waiting all threads completion for "
            + timeOut + " " + timeUnit + "...");
            // Blocks until timeout, or all threads complete
            // execution, or the current thread is interrupted
            // whichever happens first.
            boolean isTerminated = pool.awaitTermination(timeOut,timeUnit);
            System.out.println("isTerminated()=" + isTerminated);
            if(!isTerminated){
                System.out.println("Calling shutdownNow()...");
                List<Runnable> list = pool.shutdownNow();
                System.out.println(list.size() + " threads running");
                isTerminated = pool.awaitTermination(timeOut,timeUnit);
                if(!isTerminated){
                    System.out.println("Some threads still running");
                }
                System.out.println("Exiting");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}