package vip.openpark.second.cache.jdk.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author anthony
 * @version 2023/11/11 15:57
 */
@Slf4j
public class JdkSecondLevelCache implements Cache {

    /**
     * 自定义的读写锁
     */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * 自定义缓存堆
     */
    private final ConcurrentHashMap<Object, Object> cache = new ConcurrentHashMap<>(16);

    /**
     * 该缓存的唯一标识
     */
    private final String id;

    /**
     * 需要提供一个 String 参数作为 id 的构造方法
     *
     * @param id
     */
    public JdkSecondLevelCache(String id) {
        log.info("初始化自定义的缓存");
        this.id = id;
    }

    /**
     * 该缓存的唯一标识
     *
     * @return
     */
    @Override
    public String getId() {
        log.info("获取缓存的唯一标识{}", id);
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        log.info("将数据存放在自定义缓存中，缓存的key={}，缓存的value={}", key, value);
        cache.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        log.info("正在获取缓存中的数据，获取数据的key={}", key);
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        log.info("从缓存中移除数据，移除数据的key={}", key);
        return cache.remove(key);
    }

    @Override
    public void clear() {
        log.info("正在清空缓存数据{}", this);
        cache.clear();
    }

    @Override
    public int getSize() {
        log.info("{}获取缓存中的数量", this);
        return cache.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        log.info("{}正在获取读写锁", this);
        return lock;
    }
}