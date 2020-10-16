package com.example.study.common.utils;

import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 沉香微风
 */
public class MapperUtil {

    private static ThreadLocal<DateFormat> dateFormatForTime = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static ThreadLocal<DateFormat> dateFormatForDate = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private static ThreadLocal<DateFormat> dateFormatForMonth = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM"));

    /**
     * 使用MapperFactory 重新映射对象
     *
     * @param dClass 结果对象的类
     * @param s      原始对象
     * @return 结果对象
     */
    public static <D, S> S mapperObject(Class<S> dClass, D s) {
        return mapperObject(dClass, s, new HashMap<>());
    }

    /**
     * 使用MapperFactory 重新映射对象
     *
     * @param dClass 结果对象的类
     * @param s      原始对象
     * @param map    field映射（key 为原始对象的fieldName，value为结果对象的fieldName）
     * @param <S>    原始对象泛型
     * @param <D>    结果对象泛型
     * @return 结果对象
     */
    public static <S, D> D mapperObject(Class<D> dClass, S s, Map<String, String> map) {
        if (s == null) {
            return null;
        }
//        MapperFactory mapperFactory = SpringBeanContext.getBean(MapperFactory.class);
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        ClassMapBuilder classMapBuilder = mapperFactory.classMap(s.getClass(), dClass);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            classMapBuilder.field(entry.getKey(), entry.getValue());
        }
        classMapBuilder.byDefault().register();
        return mapperFactory.getMapperFacade().map(s, dClass);
    }

    /**
     * 使用MapperFactory 重新映射对象
     *
     * @param dClass 结果对象的类
     * @param list   原始对象列表
     * @param <S>    原始对象泛型
     * @param <D>    结果对象泛型
     * @return 结果对象列表
     */
    public static <S, D> List<D> mapperObjectList(Class<D> dClass, List<S> list) {
        return mapperObjectList(dClass, list, new HashMap<>());
    }

    /**
     * 使用MapperFactory 重新映射对象
     *
     * @param dClass 结果对象的类
     * @param list   原始对象列表
     * @param map    field映射（key 为原始对象的fieldName，value为结果对象的fieldName）
     * @param <S>    原始对象泛型
     * @param <D>    结果对象泛型
     * @return 结果对象列表
     */
    public static <S, D> List<D> mapperObjectList(Class<D> dClass, List<S> list, Map<String, String> map) {
        //        MapperFactory mapperFactory = SpringBeanContext.getBean(MapperFactory.class);
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        ClassMapBuilder classMapBuilder = mapperFactory.classMap(list.get(0).getClass(), dClass);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            classMapBuilder.field(entry.getKey(), entry.getValue());
        }
        classMapBuilder.byDefault().register();

        return mapperFactory.getMapperFacade().mapAsList(list, dClass);
    }

    /**
     * 使用MapperFactory 重新映射对象
     *
     * @param dClass 结果对象的类
     * @param page   原始对象列表
     * @param <S>    原始对象泛型
     * @param <D>    结果对象泛型
     * @return 结果对象列表
     */
    public static <S, D> Page<D> mapperPageObject(Class<D> dClass, Page<S> page) {
        return mapperPageObject(dClass, page, new HashMap<>());
    }

    /**
     * 使用MapperFactory 重新映射对象
     *
     * @param dClass 结果对象的类
     * @param page   原始对象列表
     * @param map    field映射（key 为原始对象的fieldName，value为结果对象的fieldName）
     * @param <S>    原始对象泛型
     * @param <D>    结果对象泛型
     * @return 结果对象列表
     */
    public static <S, D> Page<D> mapperPageObject(Class<D> dClass, Page<S> page, Map<String, String> map) {
        //        MapperFactory mapperFactory = SpringBeanContext.getBean(MapperFactory.class);
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        if (page == null) {
            return null;
        }

        if (CollectionUtils.isEmpty(page.getContent())) {
            return new PageImpl<>(new ArrayList<>(), page.getPageable(), page.getTotalElements());
        }

        ClassMapBuilder classMapBuilder = mapperFactory.classMap(page.getContent().get(0).getClass(), dClass);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            classMapBuilder.field(entry.getKey(), entry.getValue());
        }
        classMapBuilder.byDefault().register();

        return page.map(source -> mapperFactory.getMapperFacade().map(source, dClass));
    }

}
