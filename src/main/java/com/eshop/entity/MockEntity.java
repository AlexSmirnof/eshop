package com.eshop.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockEntity {
    private String str;
    private String[] arr;
    private List<Integer> list;
    private Map<String,String> map;
    private MockEntity mock;

    public MockEntity() {
        str = "stroka";
        arr = new String[]{"AAA","BBB","CCC"};
        list = Arrays.asList(123,456,789);
        map = new HashMap<String,String>(){{put("key","value");}};
        mock = this;
    }

    @Override
    public String toString() {
        return "MockEntity{" +
                "str='" + str + '\'' +
                ", arr=" + Arrays.toString(arr) +
                ", list=" + list +
                ", map=" + map +
                '}';
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public MockEntity getMock() {
        return mock;
    }

    public void setMock(MockEntity mock) {
        this.mock = mock;
    }
}
