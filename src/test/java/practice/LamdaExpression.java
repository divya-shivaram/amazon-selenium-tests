package practice;

public class LamdaExpression {
    @FunctionalInterface
    interface Calculator{
        int operation(int a, int b);
    }

    public static void main(String[] args) {
        Calculator add = (a, b) -> a+b;
        Calculator multi = (a, b) -> a*b;

        System.out.println(add.operation(2,4));
        System.out.println(multi.operation(3,9));

    }
}
