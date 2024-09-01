package com.striveonger.common.core.utils;

import cn.hutool.core.collection.CollUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 计时器
 * @author Mr.Lee
 * @since 2024-08-31 11:22
 */
public class Timepiece {
    private final Logger log = LoggerFactory.getLogger(Timepiece.class);

    private final String name;
    private final Map<Thread, List<Keep>> map = new ConcurrentHashMap<>();

    private Timepiece(String name) {
        this.name = name;
        keep("start");
    }

    public static Timepiece of(String name) {
        return new Timepiece(name);
    }

    /**
     * 打点记录
     * @param title 打点标题
     */
    public void keep(String title) {
        Thread thread = Thread.currentThread();
        List<Keep> list = map.computeIfAbsent(thread, k -> new ArrayList<>());
        list.add(new Keep(title, System.currentTimeMillis()));
    }


    public void show() {
        Thread thread = Thread.currentThread();
        synchronized (thread) {
            long now = System.currentTimeMillis();
            List<Keep> list = map.remove(thread);
            if (CollUtil.isNotEmpty(list)) {
                Keep start = list.get(0), prev = start;
                for (int i = 1; i < list.size(); i++) {
                    Keep curr = list.get(i);
                    log.info("{} cost {} ms", curr.getName(), curr.getTime() - prev.getTime());
                    prev = curr;
                }
                log.info("task: {}, total cost {} ms", name, now - start.getTime());
            }
        }
    }

    private static class Keep {
        private final String name;
        private final long time;

        public Keep(String name, long time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public long getTime() {
            return time;
        }
    }


}