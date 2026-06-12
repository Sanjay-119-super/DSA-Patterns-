package Interval_Pattern_4;

import java.util.ArrayList;
import java.util.List;

public class MyCalender1BruteForce {

    List<int[]> list;

    public MyCalender1BruteForce(){
        list=new ArrayList<>();
    }

    public boolean book(int startTime, int endTime){
        for(int[] curr : list){
            if (isOverlap(curr,startTime,endTime))
                return false;// overlap mil gya hai
        }
        list.add(new int[] {
                startTime, endTime // no overlap
        });
        return true;
    }

    private boolean isOverlap(int[] existing, int newStart, int newEnd) {
        int existingStart=existing[0],
                existingEnd=existing[1];
        return newStart<existingEnd && existingStart < newEnd;
    }
}

class Drive{
    public static void main(String[] args) {
        MyCalender1BruteForce myCalender = new MyCalender1BruteForce();

        System.out.println("book(10, 20): " + myCalender.book(10, 20));
        System.out.println("book(15, 25): " + myCalender.book(15, 25));
        System.out.println("book(20, 30): " + myCalender.book(20, 30));
    }
}