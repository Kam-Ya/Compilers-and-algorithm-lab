package engi3255.sort;

import static java.lang.Math.*;

public class AnalyzerImpl implements Analyzer{
    public double[] ratio = new double[]{};
    public double error;
    public String bigOh;
    public double mean;

    @Override
    public void analyze(int[] sizes, long[] data) {
        String[] oh = {"O(1)", "O(log N)", "O(N)", "O(N log N)", "O(N^2)", "O(N^3)", "O(2^N)"};
        double[][] OhRatios = new double[sizes.length][7];
        double[][] errors = new double[1][7];
        double[][] means = new double[1][7];

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

        for (int i = 0; i < sizes.length; i++) {
            errors[0][0] += abs(OhRatios[i][0] - means[0][0])/means[0][0];
            errors[0][1] += abs(OhRatios[i][1] - means[0][1])/means[0][1];
            errors[0][2] += abs(OhRatios[i][2] - means[0][2])/means[0][2];
            errors[0][3] += abs(OhRatios[i][3] - means[0][3])/means[0][3];
            errors[0][4] += abs(OhRatios[i][4] - means[0][4])/means[0][4];
            errors[0][5] += abs(OhRatios[i][5] - means[0][5])/means[0][5];
            errors[0][6] += abs(OhRatios[i][6] - means[0][6])/means[0][6];
        }

        errors[0][0] = errors[0][0]/sizes.length;
        errors[0][1] = errors[0][1]/sizes.length;
        errors[0][2] = errors[0][2]/sizes.length;
        errors[0][3] = errors[0][3]/sizes.length;
        errors[0][4] = errors[0][4]/sizes.length;
        errors[0][5] = errors[0][5]/sizes.length;
        errors[0][6] = errors[0][6]/sizes.length;

        int count = 0;
        for (int i = 1; i < 7; i++) {
            if (errors[0][i] < errors[0][i - 1]) {
                count = i;
            }
        }

        for (int i = 1; i < sizes.length; i++) {
            ratio[i] = OhRatios[i][count];
        }

        this.error = errors[0][count];
        this.bigOh = oh[count];

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
        return this.bigOh;
    }
}
