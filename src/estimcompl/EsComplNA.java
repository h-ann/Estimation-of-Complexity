package estimcompl;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;

public class EsComplNA {

    public int estimate(int[] sz, double[] T) {
        int l = 1 + T.length / 2;

        List rsq = new ArrayList(); //T(N^2)/T(N)
        List rdoub = new ArrayList(); //T(2N)/T(N)
        List rfdoub = new ArrayList(); // T(4N)/T(2N)
        List s = new ArrayList();
        List stemp = asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        for (int i = 0; i < l - 2; i++) {
            rsq.add(T[l + i] / T[i]);
            rdoub.add(T[i + 1] / T[i]);
            rfdoub.add(T[i + 2] / T[i + 1]);
        }

        for (int i = 0; i < l - 2; i++) {
            stemp.set(0, (double) stemp.get(0) + Math.pow(((double) rsq.get(i) - 2), 2));//for alogN
            stemp.set(1, (double) stemp.get(1) + Math.pow(((double) rsq.get(i) - 2 * sz[i]), 2));//for aNlogN
            stemp.set(2, (double) stemp.get(2) + Math.pow(((double) rsq.get(i) - 2 * sz[i] * sz[i]), 2));//for aN^2logN
            stemp.set(3, (double) stemp.get(3) + Math.pow(((double) rsq.get(i) - Math.pow(2, sz[i]*sz[i]-sz[i])), 2));//for 2^N
            stemp.set(4, (double) stemp.get(4) + Math.pow(((double) rsq.get(i) - Math.pow(sz[i], 2)), 2));//for aN^2
            stemp.set(5, (double) stemp.get(5) + Math.pow(((double) rsq.get(i) - sz[i]), 2));//for aN
        }
        for (int i = 0; i < stemp.size(); i++) {
            s.add(Math.sqrt((double) stemp.get(i) / (l - 3)));
        }
      
       int minIndex = s.indexOf(Collections.min(s)); 
        System.out.println(minIndex);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        //int[] sizes = {100, 200, 400, 800, 1600, 10000, 40000, 160000, 640000};// for marge
        int[] sizes = {100, 200, 400, 800, 10000, 40000, 160000}; // for buble
        //int[] sizes = {400, 800, 1600, 3200, 6400, 160000, 640000, 2560000, 10240000};   //for array.sort();     

       // double[] times = {0.01, 0.022, 0.048, 0.103, 0.213, 1.451, 6.157, 25.835, 110.553}; //merge
        double[] times = {0.022, 0.086, 0.340, 1.428, 252.585, 4192.996, 70269.942}; //buble
        // double[] times = {0.007, 0.009, 0.022, 0.038, 0.069, 1.716, 7.058, 33.457, 110.467};//array.sort

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
        EsComplNA e = new EsComplNA();
        e.estimate(sizes, times);

    }
}
