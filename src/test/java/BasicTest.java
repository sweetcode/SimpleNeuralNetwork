import de.sweetcode.snn.Network;
import de.sweetcode.snn.activation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class BasicTest {

    @Test
    public void testXOR() {
        Network network = new Network(1, new Logistic(), new int[]{2, 2, 1});

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

        network.train(inputs, targets, 0.5, 1_000_000);

        System.out.println(Arrays.toString(network.think(new double[]{0, 0})));
        System.out.println(Arrays.toString(network.think(new double[]{1, 0})));
        System.out.println(Arrays.toString(network.think(new double[]{0, 1})));
        System.out.println(Arrays.toString(network.think(new double[]{1, 1})));

        /*
        final double DELTA = 0.0001;
        Assert.assertArrayEquals(new double[] {0.003199323597762307}, network.think(new double[] {0, 0}), DELTA);
        Assert.assertArrayEquals(new double[] {0.9973163903246864}, network.think(new double[] {1, 0}), DELTA);
        Assert.assertArrayEquals(new double[] {0.9973165389380837}, network.think(new double[] {0, 1}), DELTA);
        Assert.assertArrayEquals(new double[] {0.003199323597762307}, network.think(new double[] {0, 0}), DELTA);
        */
    }

}
