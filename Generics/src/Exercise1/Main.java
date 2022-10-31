package Exercise1;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws Exception {

        Callable<Integer> integerCallable = new Callable<>() {
            @Override
            public Integer call() throws Exception {
                return ThreadLocalRandom.current().nextInt(10);
            }
        };

        Callable<Double> doubleCallable = new Callable<>() {
            @Override
            public Double call() throws Exception {
                return ThreadLocalRandom.current().nextDouble(10);
            }
        };

        Callable<String> stringCallable = new Callable<>() {
            @Override
            public String call() throws Exception {
                return generateRandomString();
            }

            private String generateRandomString() {
                final int STRING_LENGTH = 3;
                String AlphaBetString = "abcdefghijklmnopqrstuvxyz";
                StringBuilder sb = new StringBuilder(STRING_LENGTH);

                for (int i = 0; i < STRING_LENGTH; i++) {
                    int index = (int) (AlphaBetString.length() * Math.random());
                    sb.append(AlphaBetString.charAt(index));
                }

                return sb.toString();
            }
        };

        Double d = retry(doubleCallable, 5.5, 20, 20);
        Integer integer = retry(integerCallable, 5, 20, 100);
        String string = retry(stringCallable, "aaa", 10, 500);
    }

    public static <T> T retry(Callable<T> action, T expectedResult,
                              int numRetries, long sleepTime) throws Exception {
        T result;
        int i = 0;

        do {
            result = action.call();
            i += 1;
            Thread.sleep(sleepTime);
        }
        while (i <= numRetries && result != expectedResult);

        System.out.println("Try #" + i + ": " + result);

        return result;
    }

    public static <T> T retry(Callable<T> action,
                              T expectedResult, int numRetries) throws Exception {
        return retry(action, expectedResult, numRetries, 1000);
    }

    public static <T> T retry(Callable<T> action,
                              T expectedResult, long sleepTime) throws Exception {
        return retry(action, expectedResult, 10, sleepTime);

    }
}
