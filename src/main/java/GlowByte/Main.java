package GlowByte;

import java.util.Scanner;

/**
 * Самая сложная операция calculate, выполняется за О(МАХ(n, m),
 * где n и m длины входных чисел)
 * <p>
 * Ввод чисел производится в стандартной форме, 1 на строку,
 * но в структуре данных хранятся в обратном виде как и требует ТЗ
 * <p>
 * Вывод в обраной форме, то есть, если ввести 103 и 902 (=1005), на выход получим 5001
 */

class MyLinkedNumber{

    MyNode head = new MyNode();

    public MyLinkedNumber() {}

    public MyLinkedNumber(int number) { //конструктор записывающий число в связанный список по правилам из ТЗ
        //О(n)
        MyNode node = head;

        if (number % 10 == 0)
            node.setKey(number);
        else {
            node.setKey(number % 10);
            number /= 10;
            while (number != 0) {
                node.next = new MyNode();
                node = node.getNext();
                node.setKey(number % 10);
                number /= 10;
            }
        }
    }

    public static MyLinkedNumber calculate( //складывает 2 числа
            MyLinkedNumber number1
            , MyLinkedNumber number2) {
        MyLinkedNumber answer = new MyLinkedNumber();

        MyNode node3 = answer.head;

        MyNode node1 = number1.head;
        MyNode node2 = number2.head;

        int shift = 0; // показывает есть ли переполнение разряда
        int x;
        int y;
        boolean isHead = true; // показывает идет запись в голову или нет

        while (node1 != null || node2 != null) {

            x = node1 == null ? 0: node1.getKey();
            y = node2 == null ? 0: node2.getKey();

            if (isHead) {
                node3.setKey((x + y + shift) % 10);
                isHead = false;
            } else {
                node3.next = new MyNode((x + y + shift) % 10);
                node3 = node3.getNext();
            }

            shift = (x + y + shift) / 10;

            node1 = node1 != null ? node1.getNext() : null;
            node2 = node2 != null ? node2.getNext() : null;
        }

        if (shift == 1) // для проверки переполнения самого мтаршего разряда
            node3.next = new MyNode(1);

        return answer;
    }

    static class MyNode {
        private int key;
        private MyNode next;

        public MyNode() {}

        public MyNode(int key) {
            this.key = key;
        }

        public int getKey() { //геттер для ключа
            return this.key;
        }

        public void setKey(int key) { //сеттер для ключа
            this.key = key;
        }

        public MyNode getNext(){ //переход к следующему
            return this.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MyLinkedNumber number1 = new MyLinkedNumber(scanner.nextInt());
        MyLinkedNumber number2 = new MyLinkedNumber(scanner.nextInt());

        MyLinkedNumber answer = MyLinkedNumber.calculate(number1, number2);

        MyLinkedNumber.MyNode node_ = answer.head; //Вывод
        while (node_ != null) {
            System.out.print(node_.getKey());
            node_ = node_.getNext();
        }
    }
}