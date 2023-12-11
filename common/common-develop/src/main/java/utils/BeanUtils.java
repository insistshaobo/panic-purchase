package utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BeanUtils
 * @Description
 * @Author wangjm
 * @Date 2023/4/26
 * @Version 1.0
 */
public class BeanUtils {
    /**
     * 实体类集合转化
     *
     * @param fromObj
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copy(List<?> fromObj, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        if (fromObj.isEmpty()) {
            return list;
        }
        for (Object source : fromObj) {
            //把源对象类型强制转换为目标对象
            T target = JSON.parseObject(JSON.toJSONString(source), targetClass);
            //把源对象属性赋值给目标对象
            BeanUtil.copyProperties(source, target);
            list.add(target);
        }
        return list;
    }

    /**
     * 实体类转化
     *
     * @param fromObj
     * @param targetClass
     * @return
     */
    public static <T> T copyEntity(Object fromObj, Class<T> targetClass) {
        if (ObjectUtil.isEmpty(fromObj)) {
            return null;
        }
        //把源对象类型强制转换为目标对象
        T target = JSON.parseObject(JSON.toJSONString(fromObj), targetClass);
        //把源对象属性赋值给目标对象
        BeanUtil.copyProperties(fromObj, target);
        return target;
    }
}
