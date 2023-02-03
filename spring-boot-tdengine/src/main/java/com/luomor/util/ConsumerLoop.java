package com.luomor.util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import com.taosdata.jdbc.tmq.ConsumerRecords;
import com.taosdata.jdbc.tmq.ReferenceDeserializer;
import com.taosdata.jdbc.tmq.TaosConsumer;

public abstract class ConsumerLoop {
    private final TaosConsumer<ResultBean> consumer;
    private final List<String> topics;
    private final AtomicBoolean shutdown;
    private final CountDownLatch shutdownLatch;

    public ConsumerLoop() throws SQLException {
        Properties config = new Properties();
        config.setProperty("msg.with.table.name", "true");
        config.setProperty("enable.auto.commit", "true");
        config.setProperty("group.id", "group1");
        config.setProperty("value.deserializer", "com.taosdata.jdbc.tmq.ConsumerTest.ConsumerLoop$ResultDeserializer");

        this.consumer = new TaosConsumer<>(config);
        this.topics = Collections.singletonList("topic_speed");
        this.shutdown = new AtomicBoolean(false);
        this.shutdownLatch = new CountDownLatch(1);
    }

    public abstract void process(ResultBean result);

    public void pollData() throws SQLException {
        try {
            consumer.subscribe(topics);

            while (!shutdown.get()) {
                ConsumerRecords<ResultBean> records = consumer.poll(Duration.ofMillis(100));
                for (ResultBean record : records) {
                    process(record);
                }
            }
        } finally {
             consumer.close();
            shutdownLatch.countDown();
        }
    }

    public void shutdown() throws InterruptedException {
        shutdown.set(true);
        shutdownLatch.await();
    }

    static class ResultDeserializer extends ReferenceDeserializer<ResultBean> {

    }

    static class ResultBean {
        private Timestamp ts;
        private int speed;

        public Timestamp getTs() {
            return ts;
        }

        public void setTs(Timestamp ts) {
            this.ts = ts;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }
}