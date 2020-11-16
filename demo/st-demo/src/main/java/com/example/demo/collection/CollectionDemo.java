package com.example.demo.collection;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class CollectionDemo {


    public static void test() {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("mht");

        String s = threadLocal.get();
        threadLocal.remove();

        InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
            }
        };


        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();
        reentrantLock.lock();
        reentrantLock.unlock();

        ReentrantReadWriteLock readLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock1 = readLock.readLock();
        readLock1.lock();
        readLock1.unlock();
        ReentrantReadWriteLock.WriteLock writeLock = readLock.writeLock();
        writeLock.lock();
        writeLock.tryLock();
        writeLock.unlock();

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();


        ExecutorService pool = Executors.newFixedThreadPool(1);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ExecutorService pool1 = Executors.newWorkStealingPool();


    }

    public static void main(String[] args) {


        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
        concurrentHashMap.put("", "");
        String s = concurrentHashMap.get("");

        TreeMap treeMap = new TreeMap<String, String>();
        treeMap.put("", "");
        treeMap.get("");

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("", "");

        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("");
        String s1 = linkedList.get(1);

        LinkedHashSet<String> strings1 = new LinkedHashSet<String>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("", "");

        Set set = new HashSet();
        TreeSet<String> strings = new TreeSet<String>();


        ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>();

    }

}
