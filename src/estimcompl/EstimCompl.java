package estimcompl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import static java.lang.Math.abs;
import java.util.LinkedList;

public class EstimCompl {

    public int estimate(int[] sz, double[] T) {

        int l = 1 + T.length / 2;
        double t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, a = 0, b1 = 0, b2 = 0;
        int[] r = {0, 0, 0, 0, 0, 0};

        for (int i = 0; i < l - 2; i++) {
            t1 = T[i + 1] / T[i];        //T[2N]/T[N]
            t2 = T[i + 2] / T[i + 1];    //T[4N]/T[2N]
            t3 = T[l + i] / T[i];        //T[N^2]/T[N] 
            t4 = T[i + 2] - T[i + 1];     //T[4N]-T[2N]
            t5 = T[i + 1] - T[i];         //T[2N]-T[N]
System.out.println(t3);
            if (t1 > 1 && t1 < 2 && abs(t3 - 2) < 1) {  //alogN
                r[0] = r[0] + 1;

            }
            if (t1 > 2 && t1 < 3 && abs(t3 - 2 * sz[i]) < 1) {//aNlogN
                r[1] = r[1] + 1;

            }
            if (t1 > 4 && t1 < 5 && abs(t3 - 2 * sz[i] * sz[i]) < 1) { //aN^2logN
                r[2] = r[2] + 1;

            }
            if (abs(t1 - T[i]) < 1) {//a^N
                r[3] = r[3] + 1;

            }
            if (abs(t1 - t2) < 1) {//aN^b

                b1 = Math.log(t1) / Math.log(2);
                b2 = Math.log(t3) / Math.log(sz[i]);
                double b = (b1 + b2) / 2;

                if (abs(b1 - b2) < 1) {
                    r[4] = r[4] + 1;
                }

            }
            if (abs(t4 - 2 * t5) < 1) {  //aN+b
                a = (T[i + 1] - T[i]) / sz[i];
                b1 = T[i] - a * sz[i];
                b2 = T[l + i] - a * sz[i] * sz[i];
                if (abs(b1 - b2) < 1) {
                    r[5] = r[5] + 1;
                }
            }
        }
        System.out.println("number of checks :" + " " + (l - 2));
        for (int i = 0; i < r.length; i++) {
            System.out.println(r[i]);
        }
        return 0;
    }

//    public static void main(String[] args) throws Exception {
//        int[] sizes = {100, 200, 400, 800, 1600, 10000, 40000, 160000, 640000};// for marge
//        //int[] sizes = {100, 200, 400, 800, 10000, 40000, 160000}; // for buble
//        //int[] sizes = {400, 800, 1600, 3200, 6400, 160000, 640000, 2560000, 10240000};   //for array.sort();     
//
//        double[] times = {0.01, 0.022, 0.048, 0.103, 0.213, 1.451, 6.157, 25.835, 110.553}; //merge
//        //double[] times = {0.022, 0.086, 0.340, 1.428, 252.585, 4192.996, 70269.942}; //buble
//        // double[] times = {0.007, 0.009, 0.022, 0.038, 0.069, 1.716, 7.058, 33.457, 110.467};//array.sort

//        LinkedList str = new LinkedList();
//        FileInputStream fis = new FileInputStream("/home/ania/res.txt");
//	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//	String line = null;
//	while ((line = br.readLine()) != null) {
//            str.add(line);
//	}
//	br.close();
//        fis.close();
//        while (true) {
//            line = (String) str.get(0);
//            if (!line.startsWith("s.f."))
//                str.remove(0);
//            else
//                break;
//        }
//        int[] sizes = new int[str.size()];
//        double[] times = new double[str.size()];
//        for (int i=0; i<str.size(); i++) {
//            line = (String) str.get(i);
//            String[] arr = line.split("\\s+");
//            sizes[i] = Integer.parseInt(arr[1]);
//            times[i] = Double.parseDouble(arr[4].replace(',', '.'));
//        }
//        
      //  EstimCompl e = new EstimCompl();
     //   e.estimate(sizes, times);

  //  }

}
