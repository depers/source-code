package com.alibaba.ttl.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

public class TransmittableThreadLocalTest {


    public static void main(String[] args) throws InterruptedException {

        TransmittableThreadLocal<Integer> transmittableThreadLocal = new TransmittableThreadLocal<Integer>();
        transmittableThreadLocal.set(1);

        System.out.println("Thread: " + Thread.currentThread().getName() + ", value:" + transmittableThreadLocal.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread().getName() + ", value:" + transmittableThreadLocal.get());
                transmittableThreadLocal.set(2);
                System.out.println("Thread: " + Thread.currentThread().getName() + ", value:" + transmittableThreadLocal.get());

            }
        };

        Thread thread = new Thread(TtlRunnable.get(runnable));
        thread.start();
        thread.join();

        System.out.println("Thread: " + Thread.currentThread().getName() + ", value:" + transmittableThreadLocal.get());

    }
}
