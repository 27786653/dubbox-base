package com.yuhi.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReflectUtils {
    /**
     * 通过构造函数实例化对象
     *
     * @param className      类的全路径名称
     * @param parameterTypes 参数类型
     * @param initargs       参数值
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object constructorNewInstance(String className, Class[] parameterTypes, Object[] initargs) {
        try {
            Constructor<?> constructor = (Constructor<?>) Class
                    .forName(className).getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(initargs);
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }


    /**
     * 获取字段值
     *
     * @param propertyName 属性名
     * @param object       实例对象
     * @return 字段值
     */
    public static Object getProperty(String propertyName, Object object) {
        try {

            PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(object);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的,它支持属性连缀操作:如,.对象.属性
     *
     * @param propertyName 属性名
     * @param object       实例对象
     * @return 字段值
     */
    public static Object getBeanInfoProperty(String propertyName, Object object) {
        try {
            return BeanUtils.getProperty(object, propertyName);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以字符串形式存在的
     *
     * @param object       实例对象
     * @param propertyName 属性名
     * @param value        字段值
     * @return
     */
    public static void setBeanInfoProperty(Object object, String propertyName, String value) {
        try {
            BeanUtils.setProperty(object, propertyName, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以对象属性的实际类型
     *
     * @param propertyName 属性名
     * @param object       实例对象
     * @return 字段值
     */
    public static Object getPropertyUtilByName(String propertyName, Object object) {
        try {
            return PropertyUtils.getProperty(object, propertyName);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 通过BeanUtils工具包获取反射获取字段值,注意此值是以对象属性的实际类型,这是PropertyUtils与BeanUtils的根本区别
     *
     * @param object       实例对象
     * @param propertyName 属性名
     * @param value        字段值
     * @return
     */
    public static void setPropertyUtilByName(Object object, String propertyName, Object value) {
        try {
            PropertyUtils.setProperty(object, propertyName, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 设置字段值
     *
     * @param propertyName 属性名
     * @param value        新的字段值
     * @return
     */
    public static void setProperties(Object object, String propertyName, Object value) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException, IntrospectionException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
        Method methodSet = pd.getWriteMethod();
        methodSet.invoke(object, value);
    }


    /**
     * 设置字段值
     *
     * @param className      类的全路径名称
     * @param methodName     调用方法名
     * @param parameterTypes 参数类型
     * @param values         参数值
     * @param object         实例对象
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object methodInvoke(String className, String methodName, Class[] parameterTypes, Object[] values, Object object) {
        try {
            Method method = Class.forName(className).getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(object, values);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * @param <T>     具体对象
     * @param fileds  要进行比较Bean对象的属性值集合(以属性值为key,属性注释为value,集合从数据库中取出)
     * @param oldBean 源对象
     * @param newBean 新对象
     * @return 返回二个Bean对象属性值的异同
     */
    @SuppressWarnings("unused")
    public static <T> String compareBeanValue(Map<String, String> fileds, T oldBean, T newBean) {

        StringBuilder compares = new StringBuilder();
        String propertyName = null;
        Object oldPropertyValue = null;
        Object newPropertyValue = null;

        StringBuilder descrips = new StringBuilder();
        for (Map.Entry<String, String> entity : fileds.entrySet()) {
            propertyName = entity.getKey().toLowerCase();
            oldPropertyValue = getProperty(propertyName, oldBean);
            newPropertyValue = getProperty(propertyName, newBean);

            if (null == oldPropertyValue && null == newPropertyValue) {
                continue;
            }
            if ("".equals(oldPropertyValue) && "".equals(newPropertyValue)) {
                continue;
            }
            if (null == oldPropertyValue) {
                oldPropertyValue = "";
            }
            if (null == newPropertyValue) {
                newPropertyValue = "";
            }

            if (oldPropertyValue.equals(newPropertyValue)) {
                continue;
            }
            compares.append("字段注释: ").append(entity.getValue()).append("】").append("原属性值\"");
            if (StringUtils.isEmpty(oldPropertyValue + "")) {
                oldPropertyValue = " ";
            }
            compares.append(oldPropertyValue).append("\"现属性值\"");
            if (StringUtils.isEmpty(newPropertyValue + "")) {
                newPropertyValue = " ";
            }
            compares.append(newPropertyValue).append("\";");
        }
        return compares.toString();
    }


    /***
     * 暴力反射获取字段值
     *
     * @param obj       实例对象
     * @param fieldName 属性名
     * @return 属性值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        }
        Field targetField = getTargetField(obj.getClass(), fieldName);

        try {
            return FieldUtils.readField(targetField, obj, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getTargetField(Class<?> targetClass, String fieldName) {
        Field field = null;

        try {
            if (targetClass == null) {
                return field;
            }

            if (Object.class.equals(targetClass)) {
                return field;
            }

            field = FieldUtils.getDeclaredField(targetClass, fieldName, true);
            if (field == null) {
                field = getTargetField(targetClass.getSuperclass(), fieldName);
            }
        } catch (Exception e) {
        }

        return field;
    }

    /**
     * 设置字段值
     *
     * @param obj          实例对象
     * @param value        新的字段值
     * @return
     */
    public static void setFieldValue(Object obj, String fieldName, Object value) {
        if (null == obj) {
            return;
        }
        Field targetField = getTargetField(obj.getClass(), fieldName);
        try {
            FieldUtils.writeField(targetField, obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     * @param object : 子类对象
     * @param methodName : 父类中的方法名
     * @param parameterTypes : 父类中的方法参数类型
     * @return 父类中的方法对象
     */

    public static Method getDeclaredMethod(String interfaceName, String methodName){
        Method method = null ;
        Class<?> _class = null;
        try {
            _class = Class.forName(interfaceName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(_class==null)
            return null;
        else {
            Method[] methods = _class.getDeclaredMethods();// 获得类的方法集合
            // 遍历方法集合
            for (int i = 0; i < methods.length; i++) {
                // 获取所有getXX()的返回值
                if (methods[i].getName().equals(methodName)) // 方法返回
                    return methods[i];
            }
        }
        return getDeclaredMethod(_class.getInterfaces()[0].getCanonicalName(),methodName);
    }

    /**
     * @category 获取方法类型参数.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-12 下午 01:50
     */
    public static List<String> getMethodParameterType(String interfaceName, String methodName) {
        List<String> paramType=new ArrayList<>();
        Class<?>[] parameterTypes = getDeclaredMethod(interfaceName,methodName).getParameterTypes();;
//        if(StringUtils.isEmpty(interfaceName)||StringUtils.isEmpty(methodName))return paramType;
//        try {
//            Method method =null;
//            Method[] methods = Class.forName(interfaceName).getDeclaredMethods();
//            for (Method met:methods) {
//                if(met.getName().equals(methodName)){
//                    method=met;
//                    break;
//                }
//            }
//            Class<?>[] parameterTypes = method.getParameterTypes();
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        for (Class<?> param:parameterTypes) {
                paramType.add(param.getCanonicalName());
        }
        return paramType;
    }

    /**
     * @category 获取方法返回类型.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-12 下午 01:50
     */
    public static String getMethodTypeReturnString(String interfaceName, String methodName) {
        if(StringUtils.isEmpty(interfaceName)||StringUtils.isEmpty(methodName))return "";
        //获取返回值的类型，此处不是数组，请注意智商，返回值只能是一个
        Type genericReturnType = getDeclaredMethod(interfaceName, methodName).getGenericReturnType();
        System.out.println(genericReturnType);
        //获取返回值的泛型参数
        if(genericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType)genericReturnType).getActualTypeArguments();
            for (Type type : actualTypeArguments) {
                System.out.println(type);
            }
        }
//        return returnType!=null?returnType.getCanonicalName():null;
        return null;
    }

}