/**
 * Cook class and all Task Classes
 */
package Lab;

import java.util.concurrent.Semaphore;

/**
 * Cook Class that holds semaphores with appropriate permits (file holds all Task classes it uses too)
 */
public class Cook extends Thread {

    Semaphore sem1 = new Semaphore(1); //can start immediately
    Semaphore sem2 = new Semaphore(1); //can start immediately
    Semaphore sem3 = new Semaphore(1); //can start immediately
    Semaphore sem4 = new Semaphore(0); //waits for permits from 1 & 2
    Semaphore sem5 = new Semaphore(1); //can start immediately
    Semaphore sem6 = new Semaphore(0); //waits for permits from 3 & 4
    Semaphore sem7 = new Semaphore(0); //waits for permits from 5 & 6
    Semaphore sem8 = new Semaphore(1); //can start immediately
    Semaphore sem9 = new Semaphore(0); //waits for permits from 7 & 8

    /**
     * Initializes all task threads - each given this instance of Cook to access semaphores.
     */
    @Override
    public void run() {
        System.out.println("Beginning recipe!!");

        synchronized (this) {

            Task1 t1 = new Task1(this);
            t1.start();

            Task2 t2 = new Task2(this);
            t2.start();

            Task3 t3 = new Task3(this);
            t3.start();

            Task4 t4 = new Task4(this);
            t4.start();

            Task5 t5 = new Task5(this);
            t5.start();

            Task6 t6 = new Task6(this);
            t6.start();

            Task7 t7 = new Task7(this);
            t7.start();

            Task8 t8 = new Task8(this);
            t8.start();

            Task9 t9 = new Task9(this);
            t9.start();

        }

    }
}

/**
 * Task 1 class
 */
class Task1 extends Thread {

    Cook c;

    /**
     * Task 1 constructor
     *
     * @param c supplies instance of cook object
     */
    Task1(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem1 and goes immediately -- supplies sem4 with permit => signal task 4
     */
    @Override
    public void run() {
        try {
            c.sem1.acquire();   //immediate start
            System.out.println("1: Begin cutting onions...");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1: Finished cutting onions. (Signal 4)");
        c.sem1.release(); //finished with this
        c.sem4.release(); //signal 4

    }

}

/**
 * Task 2 class
 */
class Task2 extends Thread {

    Cook c;

    /**
     * Task 2 constructor
     *
     * @param c supplies instance of cook object
     */
    Task2(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem2 and runs immediately -- supplies sem4 with permit => signal task 4
     */
    @Override
    public void run() {
        try {
            c.sem2.acquire();
            System.out.println("2: Begin mincing meats...");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2: Finished Mincing meats. (Signal 4)");
        c.sem2.release();
        c.sem4.release(); //signal 4
    }

}

/**
 * Task 3 class
 */
class Task3 extends Thread {

    Cook c;

    /**
     * Task 3 constructor
     *
     * @param c supplies instance of cook object
     */
    Task3(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem3 and runs immediately -- supplies sem6 with permit => signal task 6
     */
    @Override
    public void run() {
        try {
            c.sem3.acquire();
            System.out.println("3: Begin slicing aubergines...");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3: Finished slicing aubergines. (Signal 6)");
        c.sem3.release();
        c.sem6.release();
    }

}

/**
 * Task 4 class
 */
class Task4 extends Thread {

    Cook c;

    /**
     * Task 4 constructor
     *
     * @param c supplies instance of cook object
     */
    Task4(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem4 and must wait for 1 & 2 (2 permits) -- supplies sem6 with permit => signal task 6
     */
    @Override
    public void run() {
        try {
            c.sem4.acquire(2);
            System.out.println("4: Begin making sauce... (1 & 2 done)");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4: Finished Making sauce. (Signal 6)");
        c.sem6.release();
    }

}

/**
 * Task 5 class
 */
class Task5 extends Thread {

    Cook c;

    /**
     * Task 5 constructor
     *
     * @param c supplies instance of cook object
     */
    Task5(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem5 and runs immediately -- supplies sem7 with permit => signal task 7
     */
    @Override
    public void run() {
        try {
            c.sem5.acquire();
            System.out.println("5: Begin finishing bechamel...");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5: Finished bechamel. (Signal 7)");
        c.sem5.release();
        c.sem7.release();
    }

}

/**
 * Task 6 class
 */
class Task6 extends Thread {

    Cook c;

    /**
     * Task 6 constructor
     *
     * @param c supplies instance of cook object
     */
    Task6(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem6 and must wait for 3 & 4 (2 permits) -- supplies sem7 with permit => signal task 7
     */
    @Override
    public void run() {
        try {
            c.sem6.acquire(2);
            System.out.println("6: Begin laying out the layers... (3 & 4 done)");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("6: Finished laying out the layers. (Signal 7)");
        c.sem7.release();
    }

}

/**
 * Task 7 class
 */
class Task7 extends Thread {

    Cook c;

    /**
     * Task 7 constructor
     *
     * @param c supplies instance of cook object
     */
    Task7(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem7 and must wait for 5 & 6 (2 permits) -- supplies sem9 with permit => signal task 9
     */
    @Override
    public void run() {
        try {
            c.sem7.acquire(2);
            System.out.println("7: Begin putting bechamel and cheese... (5 & 6 done)");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("7: Finished putting bechamel and cheese. (Signal 9)");
        c.sem9.release();

    }

}

/**
 * Task 8 class
 */
class Task8 extends Thread {

    Cook c;

    /**
     * Task 8 constructor
     *
     * @param c supplies instance of cook object
     */
    Task8(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem8 and begins immediately -- supplies sem9 with permit => signal task 9
     */
    @Override
    public void run() {
        try {
            c.sem8.acquire();
            System.out.println("8: Begin turning on oven...");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("8: Finished turning on oven. (Signal 9)");
        c.sem8.release();
        c.sem9.release();
    }

}

/**
 * Task 9 class
 */
class Task9 extends Thread {

    Cook c;

    /**
     * Task 9 constructor
     *
     * @param c supplies instance of cook object
     */
    Task9(Cook c) {
        this.c = c;
    }

    /**
     * Task acquires sem9 and must wait for 7 & 8 (2 permits) -- signals no one since last step
     */
    @Override
    public void run() {
        try {
            c.sem9.acquire(2);
            System.out.println("9: Begin cooking... (7 & 8 done)");
            sleep((long) (Math.random() * 9000 + 2000)); //2 - 11 SECONDS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("9: Finished cooking!");
    }

}


