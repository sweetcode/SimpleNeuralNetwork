import de.sweetcode.snn.Network;
import de.sweetcode.snn.activation.ActivationFunction;
import de.sweetcode.snn.activation.ArcTan;
import de.sweetcode.snn.activation.Logistic;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class BasicTest {

    //@Test
    public void testCache() {

        ActivationFunction function = new ArcTan();
        ActivationFunction functionCached = new Logistic(true, false, 1);

        boolean testTime = true;
        if(testTime) {
            Random random = new Random(1);

            double[] numbers = new double[100_000_0];

            long deltaX = 0;
            long deltaY = 0;

            for (int i = 0; i < 100; i++) {
                //--- CACHE
                long now = System.nanoTime();
                long delta = 0;
                for (double x : numbers) {
                    functionCached.cachedCalculate(random.nextDouble());
                    delta += (System.nanoTime() - now);
                    now = System.nanoTime();
                }
                deltaX += delta;

                //--- NNO CACHE
                now = System.nanoTime();
                long noDelta = 0;
                for (double x : numbers) {
                    function.calculate(random.nextDouble());
                    noDelta += (System.nanoTime() - now);
                    now = System.nanoTime();
                }

                deltaY += noDelta;
            }

            System.out.println((deltaX / 100D) / (deltaY / 100D));
        }

    }

    @Test
    public void testXOR() {
        double[][] inputs = new double[][] {
                {0, 0},
                {1, 0},
                {0, 1},
                {1, 1},
        };
        double[][] targets = new double[][] {
                {0},
                {1},
                {1},
                {0}
        };

        ActivationFunction functionA = new Logistic();
        Network a = new Network(1, functionA, new int[]{2, 2, 1});
        a.train(inputs, targets, 0.3, 100_000_000);

        System.out.println(Arrays.toString(a.think(new double[]{1, 0})));
        System.out.println(Arrays.toString(a.think(new double[]{0, 1})));
        System.out.println(Arrays.toString(a.think(new double[]{1, 1})));
        System.out.println(Arrays.toString(a.think(new double[]{0, 0})));
    }

}
