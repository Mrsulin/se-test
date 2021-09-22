package com.slc.design.behavioral.cursor;

interface Iterator<T> {

    void first();

    void next();

    boolean isDone();

    T currentItem();
}

abstract class Aggregate<T> {
    abstract Iterator<T> createIterator();
}

class ConcreteAggregate<T> extends Aggregate<T> {
    private T[] array = null;

    ConcreteAggregate(T[] array) {
        this.array = array;
    }

    @Override
    Iterator<T> createIterator() {
        return new ConcreteIterator();
    }

    class ConcreteIterator implements Iterator<T> {
        int index=-1;
        int size = 0;


        public ConcreteIterator() {
            this.size = array.length;
        }

        @Override
        public void first() {
            this.index = 0;
        }

        @Override
        public void next() {
            index++;
        }

        @Override
        public boolean isDone() {
            return index == size - 1;
        }

        @Override
        public T currentItem() {
            return array[this.index];
        }
    }

}

class Test {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ConcreteAggregate<Integer> aggregate = new ConcreteAggregate<>(array);
        Iterator<Integer> iterator = aggregate.createIterator();
        while (!iterator.isDone()) {
            iterator.next();
            System.out.println(iterator.currentItem());
        }
    }
}