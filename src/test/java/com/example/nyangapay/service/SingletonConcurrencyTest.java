package com.example.nyangapay.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SingletonConcurrencyTest {

    @Autowired
    private NotificationServiceManager notificationManager;

    @Test
    public void testSpringSingleton() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        NotificationServiceManager[] instances = new NotificationServiceManager[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.execute(() -> {
                instances[index] = notificationManager;
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        NotificationServiceManager firstInstance = instances[0];
        assertNotNull(firstInstance);
        for (int i = 1; i < threadCount; i++) {
            assertSame(firstInstance, instances[i], "Spring should guarantee a single instance of the bean");
        }
    }
}
