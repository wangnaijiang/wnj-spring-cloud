package com.wnj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtil {
    private static Logger logger = LoggerFactory.getLogger(CollectionUtil.class);

    /**
     * 集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection){
        return collection == null || collection.isEmpty();
    }

    /**
     * 获取第一个元素
     * @param list
     */
    public static <T> T firstEle(List<T> list){
        return isEmpty(list) ? null : list.get(0);
    }

    /**
     * 转stream
     * @param collection
     */
    public static <T> Stream<T> toStream(Collection<T> collection){
        return isEmpty(collection) ? Stream.empty() : collection.stream();
    }

    /**
     * 跳过空元素
     * @param originalList
     */
    public static <T> List<T> skipNull(List<T> originalList){
        return toStream(originalList)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param originalList
     * @param converter
     */
    public static <O,T> List<T> convertSkipNull(List<O> originalList, Function<O, T> converter){
        return toStream(originalList)
                .filter(Objects::nonNull)
                .map(converter) //转换
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static<V,K> Collector<V,?, Map<K, V>> toMap(Function<? super V, ? extends K> keyMapper, String msg){
        return Collectors.toMap(keyMapper, Function.identity(), useFirstAndLogError(msg));
    }

    /**
     *
     * @param keyMapper
     * @param valueMapper
     * @param msg
     * @return
     * @param <O> 原始类型
     * @param <K> 生成map的key的类型
     * @param <V> 生成map的value的类型
     */
    public static<O, K, V> Collector<O,?, Map<K, V>> toMap(Function<? super O, ? extends K> keyMapper
            , Function<? super O, ? extends V> valueMapper
            , String msg){
        return Collectors.toMap(keyMapper, valueMapper, useFirstAndLogError(msg));
    }

    private static<T> BinaryOperator<T> useFirstAndLogError(String msg){
        return (first,second) ->{
            LoggerUtil.error(logger, "重复数据合并, msg={0}, first={1}, second={2}", msg, first, second);
            return first;
        };
    }

    public static <T> List<T> sort(List<T> list, Comparator<? super T> comparator){
        if(!isEmpty(list)){
            list.sort(comparator);
        }
        return list;
    }

    public static <T> Predicate<T> isSeen(Function<? super T, ?> keyExtractor){
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) != null;
    }

    public static<T> List<List<T>> uniformSplit(Collection<T> collection, int pageSize){
        return null;
    }
}
