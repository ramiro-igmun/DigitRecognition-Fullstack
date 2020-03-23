package igmun.ramiro.neuralnetworkrestapi.utils;

import java.time.Instant;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Matrix {

  private static Random random = new Random(Instant.now().toEpochMilli());

  public static double[][] twoDHadamard(double[][] m1, double[][] m2){
    double[][] product = new double[m1.length][m1[0].length];
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1[0].length; j++) {
        product[i][j] = m1[i][j] * m2[i][j];
      }
    }
    return product;
  }

  public static double[][] twoDMatrixSum(double[][] m1, double[][] m2){
    double[][] sum = new double[m1.length][m1[0].length];
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1[0].length; j++) {
        sum[i][j] = m1[i][j] + m2[i][j];
      }
    }
    return sum;
  }

  public static double[] oneDHadamard(double[] v1, double[] v2){
    return IntStream.range(0,v1.length).asDoubleStream()
            .map(i -> v1[(int)i] * v2[(int)i]).toArray();
  }

  public static double[] oneDMatrixSum(double[] v1, double[] v2){
    return IntStream.range(0,v1.length).asDoubleStream()
            .map(i -> v1[(int)i] + v2[(int)i]).toArray();
  }

  public static double[][] getRandomMatrix(int rows, int cols) {
    return Stream.of(new double[rows][cols])
            .map(doubles -> {
              double[] newDoubles = new double[cols];
              for (int i = 0; i < cols; i++) {
                newDoubles[i] = random.nextGaussian();
              }
              return newDoubles;
            })
            .toArray(double[][]::new);
  }

  public static double[] getRandomArray(int size) {
    return DoubleStream.of(new double[size]).map(v -> random.nextGaussian()).toArray();
  }

}
