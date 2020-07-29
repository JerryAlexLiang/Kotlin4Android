package com.liang.kotlin4android.grammar;

import java.util.Objects;

/**
 * 创建日期：2020/7/28 on 8:03 PM
 * 描述:Java中的数据类
 * 作者:yangliang
 */
public class CellPhoneJava {

    private String name;
    private double price;

    public CellPhoneJava(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhoneJava that = (CellPhoneJava) o;
        return Double.compare(that.price, price) == 0 &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "CellPhoneJava{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
