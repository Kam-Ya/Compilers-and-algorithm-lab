package engi3255.sort;

import static java.lang.Math.*;

public class AnalyzerImpl implements Analyzer{
    public double[] ratio;
    public double[][] OhRatios;
    public double error;
    public int bigOh;
    public double mean;

    @Override
    public void analyze(int[] sizes, long[] data) {
        String[] oh = {"1", "logN", "N", "NlogN", "N^2", "N^3", "2^N"};

        if (sizes.length != data.length) {
            System.out.println("Error");
            return;
        }

        for (int i = 0; i < sizes.length; i++) {
            OhRatios[i][0] = (double) sizes[i];
            OhRatios[i][1] = (double) sizes[i]/log(sizes[i]);
            OhRatios[i][2] = 1;
            OhRatios[i][3] = (double) sizes[i]/(sizes[i]*log(sizes[i]));
            OhRatios[i][4] = (double) sizes[i]/pow(sizes[i], 2);
            OhRatios[i][5] = (double) sizes[i]/pow(sizes[i], 3);
            OhRatios[i][6] = (double) sizes[i]/pow(2, sizes[i]);
        }

        for (int i = 0; i < sizes.length; i++) {
            this.ratio[i] = (double) data[i]/sizes[i];
            this.mean += this.ratio[i];
            if (i == sizes.length - 1) { // gets mean
                this.mean = this.mean/i;
            }
        }

        for (int i = 0; i < sizes.length; i++) {
            this.error += abs(this.ratio[i] - this.mean) / this.mean;
            if (i == sizes.length - 1) { // gets mean
                this.error = this.error/i;
            }
        }

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
