package com.baosight.cloud.tools.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by yang on 2018/5/28.
 */
@Component
public class StringRedisOperation {

    @Autowired
    StringRedisTemplate rt;

    public StringRedisTemplate getStringRedisTemplate(){
        return this.rt;
    }

    //ttl单位为秒
    public void setStringValue(String key, long ttl, String value) {
        rt.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    public void setStringValue(String key, String value) {
        rt.opsForValue().set(key, value);
    }

    public String getStringValue(String key) {
        return rt.opsForValue().get(key);
    }

    public void delete(String key) {
        rt.delete(key);
    }

    public Long incr(String key) {
        return rt.execute((RedisCallback<Long>) connection ->
                connection.incr(rt.getStringSerializer().serialize(key)));
    }

    public Long multiIncr(String key, long incr) {
        return rt.execute((RedisCallback<Long>) connection ->
                connection.incrBy(rt.getStringSerializer().serialize(key), incr));
    }

    public Long decr(String key) {
        return rt.execute((RedisCallback<Long>) connection ->
                connection.decr(rt.getStringSerializer().serialize(key)));
    }

    public Long multiDecr(String key, long decr) {
        return rt.execute((RedisCallback<Long>) connection ->
                connection.decrBy(rt.getStringSerializer().serialize(key), decr));
    }


    /*Map*/

    public void mapPut(String mapName, String mkey, String value) {
        rt.opsForHash().put(mapName, mkey, value);
    }

    public Object mapGet(String mapName, String mKey) {
        return rt.opsForHash().get(mapName, mKey);
    }

    /*Queue*/

    public void pushToQueue(String queueName, String value) {
        rt.opsForList().rightPush(queueName, value);
    }

    public String popFromQueue(String queueName) {
        return rt.opsForList().leftPop(queueName);
    }

    /*Set*/

    public long addToSet(String setName, String... values) {
        return rt.opsForSet().add(setName, values);
    }

    public Long removeFromSet(String setName, Object... values) {
        return rt.opsForSet().remove(setName, values);
    }

    public Set<String> getSet(String setName) {
        return rt.opsForSet().members(setName);
    }

    public boolean isInSet(String setName, String value){
        return rt.opsForSet().isMember(setName, value);
    }

    /*ZSet*/

    //order by time
    public boolean addToZSet(String setName,String value){
        return rt.opsForZSet().add(setName, value, System.currentTimeMillis());
    }

    public boolean addToZSet(String setName, String value ,long score){
        return rt.opsForZSet().add(setName, value , score);
    }

    public long removeFromZSet(String setName ,String... value){
        return rt.opsForZSet().remove(setName, value);
    }

    public long removerFromZSetByScore(String setName, long startScore, long endScore){
        return rt.opsForZSet().removeRangeByScore(setName, startScore, endScore);
    }

    public boolean isInZSet(String setName, String value){
        if(ObjectUtils.isEmpty(rt.opsForZSet().score(setName,value))){
            return false;
        }else{
            return true;
        }
    }


    /*List*/

    public Long getListSize(String listName) {
        return rt.opsForList().size(listName);
    }

    public List<String> getValuesFromList(String listName, long start, long end) {
        return rt.opsForList().range(listName, start, end);
    }

    public List<String> getFullList(String listName) {
        return getValuesFromList(listName, 0, getListSize(listName) - 1);
    }

    public void rightAddToList(String listName, List<String> list) {
        rt.opsForList().rightPushAll(listName, list);
    }

    public void leftAddToList(String listName, List<String> list) {
        rt.opsForList().leftPushAll(listName, list);
    }

    public void removeFromList(String listName, long count, String value) {
        rt.opsForList().remove(listName, count, value);
    }


}
