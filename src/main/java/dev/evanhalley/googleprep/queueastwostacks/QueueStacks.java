package dev.evanhalley.googleprep.queueastwostacks;

import java.util.Stack;

public class QueueStacks {

    private final Stack<Integer> stackA;
    private final Stack<Integer> stackB;

    public static void main(String[] args) {
        QueueStacks stacks = new QueueStacks();
        stacks.enqueue(1);
        stacks.enqueue(2);
        stacks.enqueue(3);
        stacks.enqueue(4);
        System.out.println(stacks.dequeue());
        System.out.println(stacks.dequeue());
        System.out.println(stacks.dequeue());
        System.out.println(stacks.dequeue());
    }

    public QueueStacks() {
        this.stackA = new Stack<Integer>();
        this.stackB = new Stack<Integer>();
    }

    public void enqueue(int value) {

        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
        stackA.push(value);

        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
    }

    public int dequeue() {
        return stackA.pop();
    }

}
