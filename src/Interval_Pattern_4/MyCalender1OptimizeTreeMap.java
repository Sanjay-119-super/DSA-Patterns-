package Interval_Pattern_4;

import java.util.TreeMap;

//Time : O(log.n)
//Space : O(n)
public class MyCalender1OptimizeTreeMap {

    TreeMap<Integer, Integer> booked; //startTime(key) -> endTime(value)

    MyCalender1OptimizeTreeMap(){
        booked=new TreeMap<>();
    }

    /*
        [[], [10, 20], [15, 25], [20, 30]]
        booked = {10->20, 20->30}
        call -> book(20,30)
        startTime = 20
        endTime = 30

        prevStart = 10
        prevEnd = 20
    */
    public boolean book(int startTime, int endTime){

        //left start key --value
        Integer prevStart = booked.floorKey(startTime);  // 10 <=20 yes

        if (prevStart != null){
            Integer prevEnd = booked.get(prevStart); // 20>=20 no false
            if (prevEnd>startTime){
                return false;
            }
        }

        Integer  nextStart = booked.ceilingKey(startTime);

        if (nextStart != null){
            if (endTime>nextStart)
                return false;
        }
        booked.put(startTime, endTime);
        return true;
    }
}

class SmartDrive{
    public static void main(String[] args) {
        MyCalender1OptimizeTreeMap treeMap = new MyCalender1OptimizeTreeMap();


        System.out.println("book(10, 20): " + treeMap.book(10, 20));
        System.out.println("book(15, 25): " + treeMap.book(15, 25));
        System.out.println("book(20, 30): " + treeMap.book(20, 30));
    }
}