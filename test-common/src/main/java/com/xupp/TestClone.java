/**
 * Date: 2020-06-10 21:11
 * Author: xupp
 */

package com.xupp;

public class TestClone implements Cloneable {
    private TestClone(){

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, CloneNotSupportedException {
        TestClone o1=TestClone.class.newInstance();

        Object o2=o1.clone();
        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
    }
}
