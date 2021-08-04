package com.slc.datastruct.stack;

/**
 * 数组实现栈
 *
 * @author slc
 */
public class ArrayStack<T> {

    private int maxSize;

    private Object[] stack;

    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new Object[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(T element) {
        if (isFull()) {
            System.out.println("--------------------------栈满，开始扩容");
            copyToNewArray();
            System.out.println("--------------------------新容量大小" + maxSize);
        }
        ++top;
        stack[top] = element;
    }

    private void copyToNewArray() {
        int reSize = (int) (maxSize * 1.5);
        Object[] newArray = new Object[reSize];
        System.arraycopy(stack, 0, newArray, 0, stack.length);
        stack = newArray;
        maxSize = reSize;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T returnValue = (T) stack[top--];
        return returnValue;
    }

    public void elements() {
        if (!isEmpty()) {
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + "-");
            }
            System.out.println();
        }
    }

    public int priority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else if (operation == '+' || operation == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOperation(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    public int calc(int num1, int num2, char oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> numStack = new ArrayStack<>(10);
        ArrayStack<Character> operationStack = new ArrayStack<>(10);
        String expression = "3+2*6-2";
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        char ch = ' ';
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (!operationStack.isEmpty()) {
                if (operationStack.priority(ch) <= 0){
                    
                }
            }
        }


    }
}
