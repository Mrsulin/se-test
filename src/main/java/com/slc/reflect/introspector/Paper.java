package com.slc.reflect.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author slc
 */
public class Paper {


    /**
     * 自省
     */
    @org.junit.Test
    public void testClassIfEqual() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Human person = new Person("cc", "aa", "bb");
        PropertyDescriptor descriptor = new PropertyDescriptor("userName", Human.class);
        Method writeMethod = descriptor.getWriteMethod();
        Method readMethod = descriptor.getReadMethod();
        writeMethod.invoke(person, "new new new");


        Object invoke = readMethod.invoke(person);
        System.out.println();
        System.out.println("result read methods:---------" + invoke);
        String name = descriptor.getName();
        Class<?> propertyEditorClass = descriptor.getPropertyEditorClass();
        System.out.println(writeMethod);
        System.out.println(readMethod);
        System.out.println(name);
        System.out.println(propertyEditorClass);
        System.out.println("---------------");

        BeanInfo beanInfo = Introspector.getBeanInfo(person.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            System.out.println(propertyType);
        }
    }
}
