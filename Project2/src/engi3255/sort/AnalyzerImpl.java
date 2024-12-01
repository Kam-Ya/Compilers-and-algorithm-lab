package engi3255.sort;

import static java.lang.Math.*;

public class AnalyzerImpl implements Analyzer{
    public double[] ratio;
    public double[][] OhRatios;
    public double error;
    public int bigOh;
    public double[][] means;
    public double mean;

    @Override
    public void analyze(int[] sizes, long[] data) {
        String[] oh = {"1", "logN", "N", "NlogN", "N^2", "N^3", "2^N"};

        if (sizes.length != data.length) {
            System.out.println("Error");
            return;
        }

        for (int i = 0; i < sizes.length; i++) {
            OhRatios[i][0] = (double) data[i];
            OhRatios[i][1] = (double) data[i] / (log(sizes[i]) / log(2));
            OhRatios[i][2] = (double) data[i] / sizes[i];
            OhRatios[i][3] = (double) data[i] / (sizes[i] * (log(sizes[i]) / log(2)));
            OhRatios[i][4] = (double) data[i] / pow(sizes[i], 2);
            OhRatios[i][5] = (double) data[i] / pow(sizes[i], 3);
            OhRatios[i][6] = (double) data[i] / pow(2, sizes[i]);

            means[0][0] += OhRatios[i][0];
            means[0][1] += OhRatios[i][1];
            means[0][2] += OhRatios[i][2];
            means[0][3] += OhRatios[i][3];
            means[0][4] += OhRatios[i][4];
            means[0][5] += OhRatios[i][5];
            means[0][6] += OhRatios[i][6];

        }

        means[0][0] = means[0][0] / sizes.length;
        means[0][1] = means[0][1] / sizes.length;
        means[0][2] = means[0][2] / sizes.length;
        means[0][3] = means[0][3] / sizes.length;
        means[0][4] = means[0][4] / sizes.length;
        means[0][5] = means[0][5] / sizes.length;
        means[0][6] = means[0][6] / sizes.length;
    }

    @Override
    public double[] getRatios() {
        return this.ratio;
    }

    @Override
    public double getError() {
        return this.error;
    }

    @Override
    public String getBigOh() {
        return "";
    }
}
