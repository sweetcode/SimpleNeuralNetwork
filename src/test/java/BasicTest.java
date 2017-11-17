import de.sweetcode.snn.Network;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class BasicTest {

    @Test
    public void testXOR() {
        Network network = new Network(new int[]{2, 2, 1}, 1);

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

        network.train(inputs, targets, 0.3, 500_000);

        final double DELTA = 0.0001;
        Assert.assertArrayEquals(new double[] {0.003199323597762307}, network.think(new double[] {0, 0}), DELTA);
        Assert.assertArrayEquals(new double[] {0.9973163903246864}, network.think(new double[] {1, 0}), DELTA);
        Assert.assertArrayEquals(new double[] {0.9973165389380837}, network.think(new double[] {0, 1}), DELTA);
        Assert.assertArrayEquals(new double[] {0.003199323597762307}, network.think(new double[] {0, 0}), DELTA);

    }

}
