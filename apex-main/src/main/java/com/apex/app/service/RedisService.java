package com.apex.app.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis Service interface
 *
 * @author Mingze Ma
 */
public interface RedisService {

    /**
     * Save value
     */
    void set(String key, Object value, long time);

    /**
     * Save value
     */
    void set(String key, Object value);

    /**
     * Get value
     */
    Object get(String key);

    /**
     * Delete value
     */
    Boolean del(String key);

    /**
     * Delete values
     */
    Long del(List<String> keys);

    /**
     * Set expired time
     */
    Boolean expire(String key, long time);

    /**
     * Get expired time
     */
    Long getExpire(String key);

    Boolean hasKey(String key);

    /**
     * Order INC by delta
     */
    Long incr(String key, long delta);

    /**
     * Order DECR by delta
     */
    Long decr(String key, long delta);

    /**
     * Get the attributes in the Hash structure
     */
    Object hGet(String key, String hashKey);

    /**
     * Set the attributes in the Hash structure
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * Set the attributes in the Hash structure
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * Get the entire Hash structure directly
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * Set the entire Hash structure directly
     */
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * Set the entire Hash structure directly
     */
    void hSetAll(String key, Map<String, ?> map);

    /**
     * Deleting attributes from the Hash structure
     */
    void hDel(String key, Object... hashKey);

    /**
     * Determine if the property is present in the Hash structure
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * Incremental attributes in Hash structures
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * Decremental attributes in Hash structures
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * Get Set structure
     */
    Set<Object> sMembers(String key);

    /**
     * Adding properties to the Set structure
     */
    Long sAdd(String key, Object... values);

    /**
     * Adding properties to the Set structure
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * Whether it is a property in Set
     */
    Boolean sIsMember(String key, Object value);

    /**
     * Get the length of the Set structure
     */
    Long sSize(String key);

    /**
     * Deleting properties from a Set structure
     */
    Long sRemove(String key, Object... values);

    /**
     * Get the properties in the List structure
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * Get the length of the List structure
     */
    Long lSize(String key);

    /**
     * Get the properties in a List by index
     */
    Object lIndex(String key, long index);

    /**
     * Adding properties to a List structure
     */
    Long lPush(String key, Object value);

    /**
     * Adding properties to a List structure
     */
    Long lPush(String key, Object value, long time);

    /**
     * Adding properties to a List structure in bulk
     */
    Long lPushAll(String key, Object... values);

    /**
     * Adding properties to a List structure in bulk
     */
    Long lPushAll(String key, Long time, Object... values);

    /**
     * Removing properties from a List structure
     */
    Long lRemove(String key, long count, Object value);

}
