package com.example.basedemo.test;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-10-30 11:30
 **/
public class Synz implements Runnable {
    private int count = 100;

    public static void main(String[] args) {
        Synz ts = new Synz();
        Thread t1 = new Thread(ts, "线程1");
        Thread t2 = new Thread(ts, "线程2");
        Thread t3 = new Thread(ts, "线程3");

        t1.start();
        t2.start();
        t3.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (count > 0) {
                    count--;
                    System.out.println(Thread.currentThread().getName() + " count = " + count);
                } else {
                    break;
                }
            }

        }
    }


}
