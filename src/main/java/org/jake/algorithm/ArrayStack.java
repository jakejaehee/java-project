package org.jake.algorithm;

public class ArrayStack {
    private int[] array;
    private int topIdx = -1;

    public ArrayStack(int size) {
        this.array = new int[size];
    }

    public void push(int value) {
        if (topIdx + 1 < array.length) {
            array[++topIdx] = value;
            System.out.println("Pushed " + value + " at " + topIdx);
        } else {
            System.out.println("Stack overflows");
        }
    }

    public int pop() {
        if (topIdx >= 0) {
            System.out.println("Poped " + array[topIdx] + " at " + topIdx);
            return array[topIdx--];
        } else {
            System.out.println("Stack is empty");
            return -1;
        }
    }

    public int peek() {
        if (topIdx >= 0) {
            System.out.println("Peeked " + array[topIdx] + " at " + topIdx);
            return array[topIdx];
        } else {
            System.out.println("Stack is empty");
            return -1;
        }
    }

    public void printStack() {
        System.out.print("Stack: ");
        for (int i = 0; i <= topIdx; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push(0);
        stack.printStack();
        stack.push(1);
        stack.printStack();
        stack.push(2);
        stack.printStack();
        stack.pop();
        stack.printStack();
        stack.pop();
        stack.printStack();
        stack.peek();
        stack.printStack();
        stack.push(8);
        stack.printStack();
        stack.pop();
        stack.printStack();
        stack.pop();
        stack.printStack();
        stack.pop();
        stack.printStack();
        stack.push(99);
        stack.printStack();
    }
}
