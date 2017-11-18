import de.sweetcode.snn.Network;
import de.sweetcode.snn.activation.ActivationFunction;
import de.sweetcode.snn.activation.ArcTan;
import de.sweetcode.snn.activation.Logistic;
import de.sweetcode.snn.activation.SoftExponential;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

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


        long now = System.nanoTime();

        long deltaA = 0;
        ActivationFunction functionA = new Logistic();
        Network a = new Network(1, functionA, new int[]{2, 2, 1});
        a.train(inputs, targets, 0.5, 10_000_000);
        System.out.println(deltaA = (System.nanoTime() - now));

        now = System.nanoTime();
        long deltaB = 0;
        ActivationFunction functionB = new SoftExponential(0.5, true, true, 1);
        Network b = new Network(1, functionB, new int[]{2, 2, 1});
        b.train(inputs, targets, 0.5, 10_000_000);
        System.out.println(deltaB = (System.nanoTime() - now));


        System.out.println(functionB.getHitRate());
        System.out.println(functionB.getMissRate());

        // final double DELTA = 0.0001;
        // Assert.assertArrayEquals(new double[] {0.002238339854384448}, a.think(new double[] {0, 0}), DELTA);
        // Assert.assertArrayEquals(new double[] {0.9980892762988295}, a.think(new double[] {1, 0}), DELTA);
        // Assert.assertArrayEquals(new double[] {0.9980885212118851}, a.think(new double[] {0, 1}), DELTA);
        // Assert.assertArrayEquals(new double[] {0.002238339854384448}, a.think(new double[] {0, 0}), DELTA);
    }

}
