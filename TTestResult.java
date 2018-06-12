package LeetCode;

import org.apache.commons.math3.stat.inference.*;
public class TTestResult {
    public static void main(String[] args){
        double[] sample1 = {71.01,60.48, 58.1};
        double[] sample2 = {66.74,66.87, 57.51,54.44};
        double t_statistic;
        TTest ttest = new TTest();
        t_statistic = ttest.homoscedasticTTest(sample1, sample2);
        System.out.println(Double.toString( t_statistic) );
    }
}