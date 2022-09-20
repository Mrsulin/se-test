package com.slc.jvm.gc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

public class GcTestForMemoryLeak {

    public static void main(String[] args) throws InterruptedException {
        testForSetUpdate();

    }

    private static void testForSetUpdate() {
        Set<DataSave> set = new HashSet<>();
        DataSave d1 = new DataSave("1");
        DataSave d2 = new DataSave("2");
        DataSave d3 = new DataSave("3");
        set.add(d1);
        set.add(d2);
        set.add(d3);
        System.out.println("总共有:" + set.size() + " 个元素!");
        d3.setValue("33");
        set.remove(d3);
        set.add(d3);
        System.out.println("总共有:" + set.size() + " 个元素!");
        System.out.println(set);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataSave {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DataSave dataSave = (DataSave) o;

            return value != null ? value.equals(dataSave.value) : dataSave.value == null;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

}
