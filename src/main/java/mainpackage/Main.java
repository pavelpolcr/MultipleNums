package mainpackage;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //--generate test arrays
        long startTime = System.nanoTime();
        int[] nums = generateBigArray();
        long generateTime=System.nanoTime();
        System.out.print("Generovani  - 1 mil nahodnych int cisel cas:");
        System.out.println((generateTime-startTime)/1000000);
        startTime = System.nanoTime();
        int[] nums2 = new int[1000000];
        for(int i = 0; i<nums2.length;i++){
            nums2[i]=i;
        }
        nums2[625623]=1900; // just one duplicity in Test n. 2
        System.out.print("Generovani  - 1 mil cisel, pouze 1 duplicita: ");
        System.out.println((generateTime-startTime)/1000000);
        generateTime=System.nanoTime();
        //--Finding duplicities - measuring raw method execution time.
        startTime = System.nanoTime();
        Integer[] output = evaluate(nums);
        long maptime = System.nanoTime();
        System.out.print("Reseni pomoci map:");
        System.out.println((maptime-startTime)/1000000);
        startTime = System.nanoTime();
        Integer[] output2 = evaluate2(nums);
        long sortTime = System.nanoTime();
        System.out.print("Reseni pomoci sort, pole a listu:");
        System.out.println((sortTime-startTime)/1000000);
        startTime = System.nanoTime();
        Integer[] output3 = evaluate(nums2);
        maptime = System.nanoTime();
        System.out.print("Test 2 Reseni pomoci map:");
        System.out.println((maptime-startTime)/1000000);
        startTime = System.nanoTime();
        Integer[] output4 = evaluate2(nums2);
        sortTime = System.nanoTime();
        System.out.print("Test 2 Reseni pomoci sort, pole a listu:");
        System.out.println((sortTime-startTime)/1000000);

        //--just checking results from Test 2
        for(int n:output3) {
            System.out.println(n);
       }
        for(int n:output4) {
            System.out.println(n);
        }


    }
    public static Integer[] evaluate(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                if(map.get(nums[i]) == 0){
                    map.replace(nums[i],1);
                }
            }
            else{
                map.put(nums[i],0);
            }

        }

        map.entrySet().removeIf(r->r.getValue()==0);
        return map.keySet().toArray(new Integer[map.size()]);

    }
    public static int[] generateBigArray(){
        Random rand = new Random();
        int[] arr = new int[1000000];
        for(int i = 0;i<arr.length;i++){
            arr[i]=rand.nextInt();
        }
        return arr;
    }
    public static Integer[] evaluate2(int[] nums){
        Arrays.sort(nums);
        List<Integer> out = new ArrayList<>();

        for (int i = 1; i < nums.length; i++) {

            if (nums[i]==nums[i-1]) {
                if(!out.contains(nums[i])) {
                    out.add(nums[i]);
                }
            }
        }

          return out.toArray(new Integer[out.size()]);
    }
        /*
        int[] numbers = {1, 2, 5, 5, 6, 8, 9, 9, 7, 4, 1};
        IntStream numStream = Arrays.stream(numbers);
        numStream.forEach(Main::evaluate);
        for (int num : numbers) {
            evaluate(num);
        }

    }

    static void evaluate(int number) {
        if (used.contains(number)) {
            System.out.println(number);
        } else {
            used.add(number);
        }
*/

}
