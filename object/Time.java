package object;

import java.util.Scanner;

public class Time {
    private int hours;
    private int minutes;
    private String strTime;
    private final static Scanner obj = new Scanner(System.in);
    public Time(String time){
        if(checkTime(time)) {
            this.hours = Integer.parseInt(""+time.charAt(0)+time.charAt(1));
            this.minutes = Integer.parseInt(""+time.charAt(3)+time.charAt(4));
            this.strTime=time;
        }else enterTime();


    }

    @Override
    public String toString() {
        return hours + ":" + minutes;
    }

    public String getStrTime() {
        return strTime;
    }

    public static boolean checkTime(String time){
        int hours = Integer.parseInt(""+time .charAt(0)+time.charAt(1));
        int minutes = Integer.parseInt(""+time.charAt(3)+time.charAt(4));
        if((hours>24 || hours<0) & (minutes>60 || minutes<0)){
            System.out.println("Час введено неправильно, спробуйте щераз");
            return false;
        }
        return true;
    }

    private static String enterTime() {
        System.out.println("Введіть час у форматі [години:хвилини]");
        String timeToCheck = obj.nextLine();

        if(checkTime(timeToCheck)) {
            System.out.println("Час введено неправильно, спробуйте щераз");
            enterTime();
        }

        return timeToCheck;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
