package com.liang.kotlin4android.grammar;

/**
 * 创建日期：2020/7/29 on 6:53 PM
 * 描述:Java单例类 - 单例模式的工作机制
 * 作者:yangliang
 */
public class SingletonJava {

    private static SingletonJava instance;

    //1、首先，为了禁止外部创建SingletonJava实例，需要使用private关键字将SingletonJava的构造函数私有化
    private SingletonJava() {
    }

    //2、给外部提供一个getInstance()静态方法用于获取SingletonJava实例
    public synchronized static SingletonJava getInstance() {
        //3、判断如果当前缓存的SingletonJava实例为null，就创建一个新的实例
        if (instance == null) {
            instance = new SingletonJava();
        }
        //4、否则，直接返回缓存的实例即可
        return instance;
    }

    public void SingletonJavaTest(){
        System.out.println("SingletonJava is created...");
    }
}
