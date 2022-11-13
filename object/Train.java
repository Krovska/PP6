package object;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Train {
    private final int trainNumber;
    private int passengersNumber=0;
    private int numberOfCars=0;
    private Time start;
    private Time arrive;
    private String destination;
    private List<Car> cars = new ArrayList<>();


    public Train(int trainNumber, String destination, Time startTime, Time arriveTime){
        this.trainNumber=trainNumber;
        this.destination=destination;
        this.start = startTime;
        this.arrive = arriveTime;
    }

    public static void setSequenceNumbers(List<Car> cars){
        for(int i= 0; i<cars.size(); i++)
            cars.get(i).setSequenceNumber(i+1);
    }

    public void addCar(Car car){
        this.cars.add(car);
        car.setTrainNumber(this.trainNumber);
    }

    public Car delCar(int sequenceNumber){
        Car deletedCar = cars.get(sequenceNumber);
        this.cars.remove(sequenceNumber);
        return deletedCar;
    }


    public int getTrainNumber() {
        return trainNumber;
    }

    public int getNubberOfCars(){
        numberOfCars=cars.size();
        return numberOfCars;
    }

    public String getDestination() {
        return destination;
    }

    public int getPassengersNumber() {
        for(Car car: cars)
            this.passengersNumber+=car.getPassengersNumber();

        return passengersNumber;
    }

    @Override
    public String toString() {
        return " Номер поїзда-"+trainNumber+ " " +destination + " Час відправлення-"+start.getStrTime() +" Час прибуття-"+arrive.getStrTime()+ " Кількість вагонів-" +cars.size() ;
    }

    Comparator<Car> carComfortRatingComparator = new Comparator<Car>(){

        @Override
        public int compare(Car o1, Car o2) {
            return o1.getComfortClass().getComfortRating() - o2.getComfortClass().getComfortRating();
        }


    };
    public List<Car> getSortCarsByPasangerNumber(){
        List<Car> sortedCars = cars;
        sortedCars.sort(carComfortRatingComparator);
        return sortedCars;
    }



}
