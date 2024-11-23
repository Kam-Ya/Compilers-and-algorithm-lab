package engi3255.sort;

import static java.lang.Math.abs;

public class AnalyzerImpl implements Analyzer{
    public double[] ratio;
    public double error;
    public String bigOh;
    public double mean;

    @Override
    public void analyze(int[] sizes, long[] data) {
        if (sizes.length != data.length) {
            System.out.println("Error");
            return;
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
