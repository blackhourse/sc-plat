package cn.boot.st.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Author shiyj
 * @Description Redis工具类
 * @Date 2019-04-23 10:20
 */
@Slf4j
@Component
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key  键
     * @param time 时间(秒)
     * @return
     * @Description 指定缓存失效时间
     */

    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("设置过期时间失败", e);
            return false;
        }

    }

    /**
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     * @Description 根据key 获取过期时间
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * @param key 键
     * @return true 存在 false不存在
     * @Description 判断key是否存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("判断key是否存在失败", e);
            return false;
        }
    }


    /**
     * @param key 可以传一个值 或多个
     * @Description 删除缓存
     */

    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * @param key 键
     * @return 值
     * @Description 普通缓存获取
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     * @Description 普通缓存放入
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("保存失败", e);
            return false;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于 如果time小于等于 将设置无限期
     * @return true成功 false 失败
     * @Description 普通缓存放入并设置时间
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("保存失败", e);
            return false;
        }
    }


    /**
     * @param key   键
     * @param delta 要增加几(大于)
     * @return
     * @Description 递增
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * @param key   键
     * @param delta 要增加几(大于)
     * @return long
     * @Description long值递增并设置过期时间
     */
    public long incrAndTime(String key, long delta, long time) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        long value = redisTemplate.opsForValue().increment(key, delta);
        if (time > 0) {
            expire(key, time);
        }
        return value;
    }

    /**
     * @param key   键
     * @param delta 要增加几(大于)
     * @return
     * @Description 递增
     */
    public Double incrDouble(String key, double delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * @param key   键
     * @param delta 要增加几(大于)
     * @return Double
     * @Description double值递增并设置过期时间
     */
    public Double incrDoubleAndTime(String key, double delta, long time) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        Double value = redisTemplate.opsForValue().increment(key, delta);
        if (time > 0) {
            expire(key, time);
        }
        return value;
    }

    /**
     * @param key   键
     * @param delta 要减少几(小于)
     * @return
     * @Description 递减
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }


    // ================================Map=================================

    /**
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     * @Description HashGet
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }


    /**
     * @param key 键
     * @return 对应的多个键值
     * @Description 获取hashKey对应的所有键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     * @Description HashSet
     */
    public boolean hmset(String key, Map<String, ?> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("保存失败", e);
            return false;
        }

    }


    /**
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     * @Description HashSet 并设置时间
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("保存失败", e);
            return false;
        }

    }


    /**
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     * @Description 向一张hash表中放入数据, 如果不存在将创建
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("保存失败", e);
            return false;
        }

    }


    /**
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     * @Description 向一张hash表中放入数据, 如果不存在将创建
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("保存失败", e);
            return false;
        }
    }


    /**
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     * @Description 删除hash表中的值
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    /**
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     * @Description 判断hash表中是否有该项的值
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于)
     * @return
     * @Description hash递增 如果不存在,就会创建一个 并把新增后的值返回
     */
    public Long hincr(String key, String item, int by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于)
     * @return
     * @Description hash递增 如果不存在,就会创建一个 并把新增后的值返回
     */
    public Double hincr(String key, String item, double by, long time) {
        Double value = redisTemplate.opsForHash().increment(key, item, by);
        if (time > 0) {
            expire(key, time);
        }
        return value;
    }

    /**
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于)
     * @return
     * @Description hash递减
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }



    public Cursor<Map.Entry<Object, Object>> hscan(String key, ScanOptions options){
       return  redisTemplate.opsForHash().scan(key, options);


    }

    // ============================set=============================

    /**
     * @param key 键
     * @return
     * @Description 根据key获取Set中的所有值
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("操作失败", e);
            return null;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     * @Description 根据value从一个set中查询, 是否存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("操作失败", e);
            return false;
        }
    }


    /**
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     * @Description 将数据放入set缓存
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("操作失败", e);
            return 0;
        }
    }


    /**
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     * @Description 将set数据放入缓存
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error("操作失败", e);
            return 0;
        }
    }


    /**
     * @param key 键
     * @return
     * @Description 获取set缓存的长度
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("操作失败", e);
            return 0;
        }
    }


    /**
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     * @Description 移除值为value的
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error("操作失败", e);
            return 0;
        }
    }

    // ===============================list=================================

    /**
     * @param key   键
     * @param start 开始
     * @param end   结束  到 -代表所有值
     * @return
     * @Description 获取list缓存的内容
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("操作失败", e);
            return null;
        }
    }


    /**
     * @param key 键
     * @return
     * @Description 获取list缓存的长度
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("操作失败", e);
            return 0;
        }
    }


    /**
     * @param key   键
     * @param index 索引 index>=时，  表头， 第二个元素，依次类推；index<时，-，表尾，-倒数第二个元素，依次类推
     * @return
     * @Description 通过索引 获取list中的值
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("操作失败", e);
            return null;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @return
     * @Description 将list放入缓存
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("操作失败", e);
            return false;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     * @Description 将list放入缓存
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("操作失败", e);
            return false;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @return
     * @Description 将list放入缓存
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("操作失败", e);
            return false;
        }
    }


    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     * @Description 将list放入缓存
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("操作失败", e);
            return false;
        }
    }

    /**
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     * @Description 根据索引修改list中的某条数据
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("操作失败", e);
            return false;
        }
    }

    /**
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     * @Description 移除N个值为value
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            log.error("操作失败", e);
            return 0;
        }
    }

    // ============================ZSet=============================

    /**
     * @param key
     * @param value
     * @param score
     * @Description 添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
     */
    public Boolean zAdd(String key, String value, double score) {
        try {
            return redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            log.error("操作失败", e);
            return false;
        }
    }


    /**
     * @param key
     * @param values
     * @Description 删除元素 zrem
     */
    public Long zRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForZSet().remove(key, values);
        } catch (Exception e) {
            log.error("操作失败", e);
            return 0L;
        }
    }


    /**
     * @param key
     * @param value
     * @param score
     * @Description score的增加or减少 zincrby
     */
    public Double zIncrScore(String key, String value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }


    /**
     * @param key
     * @param value
     * @return
     * @Description 查询value对应的score   zscore
     */
    public Long zScore(String key, String value) {
        Long result = 0L;
        Double score = redisTemplate.opsForZSet().score(key, value);
        if (score != null) {
            result = redisTemplate.opsForZSet().score(key, value).longValue();
        }
        return result;
    }


    /**
     * @param key
     * @param value
     * @return
     * @Description 判断value在zset中的排名  zrank
     */
    public Long rank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * @param key
     * @return
     * @Description 返回集合的长度
     */
    public Long size(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     * @Description 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange 返回有序的集合，score小的在前面
     */
    public Set<Object> zRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     * @Description 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
     */
    public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScore(String key, int start, int end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     * @Description 查询集合中指定顺序的值  zrevrange 返回有序的集合中，score大的在前面
     */
    public Set<Object> zRevRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * @param key
     * @param min
     * @param max
     * @return
     * @Description 根据score的值，来获取满足条件的集合  zrangebyscore
     */
    public Set<Object> zSortRange(String key, int min, int max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Long addHyperLogLogOperations(String key, String value) {
        HyperLogLogOperations<String, Object> hyperLogLog = redisTemplate.opsForHyperLogLog();
        return hyperLogLog.add(key, value);
    }

    public Long getHyperLogLogOperationsSize(String Key) {
        HyperLogLogOperations<String, Object> hyperLogLog = redisTemplate.opsForHyperLogLog();
        return hyperLogLog.size(Key);
    }


    /**
     * 分布式加锁是否成功
     *
     * @param key    key值
     * @param expire 过期时间
     * @return
     */
    public boolean addLock(String key, int expire) {
        //设置未来时间value值（当前时间+过期时间）
        //把秒转成毫秒
        expire = expire * 1000;
        Long value = System.currentTimeMillis() + expire;
        //如果为空，则赋值；   如果存在或重复则返回false
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (aBoolean) {
            //如果返回为true代表加锁成功
            return true;
        } else {
            //如果为false 要判断这个锁是否已经过期了（设置过期时间就是没了避免死锁）
            //获得锁的value值
            Long oldValue = (Long) redisTemplate.opsForValue().get(key);
            //如果这个锁的value（时间+过期时间=未来时间）小于当前时间 则表示已经过期了
            if (oldValue < System.currentTimeMillis()) {
                //需要重新加一把锁
                Long newValue = System.currentTimeMillis() + expire;
                //重新set进去 这个方法返回的是 key 原来的旧值
                Long oldKeyValue = (Long) redisTemplate.opsForValue().getAndSet(key, newValue);
                if (oldKeyValue.equals(oldValue)) {
                    //如果返回原来的旧值就和我们一开始get到的值是一样的话那就证明加锁成功
                    return true;
                }
            }
            return false;
        }
    }

}
