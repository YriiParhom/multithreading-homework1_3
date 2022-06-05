import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final int SIZE = 100000000;

    public static void main(String[] args) {
        System.out.println("Однопоточный метод расчета:");
        long start = System.currentTimeMillis();
        int[] numbers = createFilledArray();
        int count = 0;
        for (int value : numbers){
            count += value;
        }
        System.out.println("Сумма значений массива: " + count);
        System.out.println("Среднне фрифметическое массива: " + (count / numbers.length));
        long finish = System.currentTimeMillis();
        System.out.println("Время выполнения расчета: " + (finish - start));

        System.out.println();
        System.out.println("Многопоточный метод расчета: ");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start2 = System.currentTimeMillis();
        int[] numbers2 = createFilledArray();
        int sum = forkJoinPool.invoke(new ArraySumTask(0, numbers2.length, numbers2));
        System.out.println("Сумма значений массива: " + sum);
        System.out.println("Среднне фрифметическое массива: " + (sum / numbers2.length));
        long finish2 = System.currentTimeMillis();
        System.out.println("Время выполнения расчета: " + (finish2 - start2));
    }

    public static int[] createFilledArray(){
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++){
            arr[i] = i;
        }
        return arr;
    }
}
