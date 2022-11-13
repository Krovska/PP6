package menu;

import file.AddToFile;
import file.OutputFromFile;
import object.Car;
import object.ComfortRating;
import object.Time;
import object.Train;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Math.abs;
public class TrainService {
    private static final Scanner obj = new Scanner(System.in);
    public static Map<Integer, Train> trains = new HashMap<>();
    public static Map<Integer, Car> cars = new HashMap<>();

    public static AddToFile trainsFile = new AddToFile("trains");
    public static AddToFile carsFile = new AddToFile("cars");
    public static AddToFile actionsFile = new AddToFile("actions");
    private static String tmpStr;

    public static Car createCar(){
       int[] intCatInfo = enterCarInfo();
       Car car = new Car(intCatInfo[0], intCatInfo[1], addComfortRating() , intCatInfo[2], intCatInfo[3]);
        if(cars.get(car.getCarNumber())==null){
            cars.put(car.getCarNumber(), car);
            tmpStr = "Додано вагон "+car;
            System.out.println(tmpStr);
            carsFile.addToFile(tmpStr);
        }

        else System.out.println("Вагон з даним номером вже наявний в базі");

        return car;
    }

  static int[] enterCarInfo(){
        int[] carIntInfo = new int[4];
        System.out.println("Введіть номер вагону:");
        carIntInfo[0] = abs(obj.nextInt());
        obj.nextLine();
        System.out.println("Введіть кількість місць:");
        carIntInfo[1] = abs(obj.nextInt());
        obj.nextLine();
        carIntInfo[2] = setPassengersNumber(carIntInfo[1]);
        System.out.println("Введіть кількість багажу:");
        carIntInfo[3] = abs(obj.nextInt());
        obj.nextLine();

        return carIntInfo;
    }

    static int setPassengersNumber(int seatsNumber){
      int passengersNumber;
      System.out.println("Введіть кількість пасажирів:");
      passengersNumber = obj.nextInt();
      obj.nextLine();
      if(passengersNumber>seatsNumber){
          System.out.println("Ви неправильно ввели кількість пасажирів:");
          setPassengersNumber(seatsNumber);
      }
      return passengersNumber;
    }

    static ComfortRating addComfortRating(){
        System.out.println("Введіть рейтинг комфорту вагону\n1 - сидячі місця, 2 - плацкарт, 3 - купе, 4 - люкс");
        int choseRating = obj.nextInt();
        obj.nextLine();
        ComfortRating comfortRating = ComfortRating.SEATING;
        switch (choseRating) {
            case 1 -> {}
            case 2 -> comfortRating = ComfortRating.SECONDCLASS;
            case 3 -> comfortRating = ComfortRating.FIRSTCLASS;
            case 4 -> comfortRating = ComfortRating.LUXE;
            default -> {
                System.out.println("Ви неправильно ввели рейтинг");
                addComfortRating();
            }
        }
        return comfortRating;
    }
    public static void showCarWithNumber(){
        if(!cars.isEmpty()){
            tmpStr = "Знайдео вагон "+findCarWithNumber();
            System.out.println(tmpStr);
            actionsFile.addToFile(tmpStr);
        }else
            System.out.println("У списку немає вагонів");

    }

    private static Car findCarWithNumber(){
        System.out.println("Введіть номер вагону для пошуку:");
        int number = obj.nextInt();
        obj.nextLine();
        if(cars.get(number) == null){
            System.out.println("Вагона з таким номером не існує, для повторення натисніть 1");
            int repeat = obj.nextInt();
            obj.nextLine();
            if(repeat==1)
                showCarWithNumber();
        }

        return cars.get(number);
    }


    public static void createTrain(){

        Train train = new Train(enterTainNumber(), enterDestination(), enterTime(1), enterTime(2));
        tmpStr = "Додано поїзд"+train;
        System.out.println(tmpStr);
        trainsFile.addToFile(tmpStr);
        for(int i=enterNumberOfCarsInTrain(); i>0; i--){
            addCarToTrain(train);
        }

        trains.put(train.getTrainNumber(), train);

    }

    private static int enterTainNumber(){
        System.out.println("Введіть номер поїзда для створення:");
        int trainNumber = abs(obj.nextInt());
        obj.nextLine();
        if(trains.get(trainNumber)!=null){
            System.out.println("Поїзд з даним номером вже наявний в базі. Введіть інший номер.");
            enterTainNumber();
        }
        return trainNumber;
    }

    private static String enterDestination(){
        System.out.println("Введіть маршрут поїзда:");
        return obj.nextLine();
    }

    private static Time enterTime(int startOrArrive){
        String startOrArriveStr = startOrArrive==1 ? "відправлення":"прибуття";
        System.out.println("Введіть час " + startOrArriveStr + " у форматі [години:хвилини]");

        return new Time(obj.nextLine());
    }

    private static int enterNumberOfCarsInTrain(){
        System.out.println("Введіть кількість вагонів для додавання:");
        int carToAddNumber = obj.nextInt();
        obj.nextLine();
        if(carToAddNumber<1){
            System.out.println("Ви неправильно ввели кількість вагонів, спробуйте ще раз");
            enterNumberOfCarsInTrain();
        }
        return carToAddNumber;
    }

    public static void addCarToTrain(Train train){
        if(cars.isEmpty())
            System.out.println("Недостатньо вагонів для додавання");
        else {
            Car adedCar = findCarWithNumber();
            if(adedCar!=null){
                train.addCar(adedCar);
                System.out.println("Вагон успішно додано");
            }else {
                System.out.println("Не вдалось додати вагон, оскільки його не знайдено. Якщо бажаєте створити новий вагон, а пізніше додати, натисніть 1:");
                if(obj.nextInt()==1)
                    train.addCar(createCar());
            }
        }
    }

    public void addCarToTrain(){
        if(!cars.isEmpty()){
            Train train = findTrainWithNumber();
            Car adedCar = findCarWithNumber();
            if(adedCar!=null){
                train.addCar(adedCar);
                System.out.println("Вагон успішно додано");
                tmpStr = "Вагон" + adedCar + " успішно додано";
                actionsFile.addToFile(tmpStr);
            }else {
                System.out.println("Не вдалось додати вагон, оскільки його не знайдено. Якщо бажаєте створити новий вагон, а пізніше додати, натисніть 1:");
                if(obj.nextInt()==1)
                    train.addCar(createCar());
            }
        }



    }
    public void delCarFromTrain(){
        Train train = findTrainWithNumber();
        System.out.println("Введіть порядковий номер вагона для видалення");
        int sequence = obj.nextInt();
        obj.nextLine();
        if(sequence>train.getNubberOfCars()){
            System.out.println("В поїзді недостатня кількість вагонів, видалено останній вагон. Інофрмація про вагон: \n"+train.delCar(train.getNubberOfCars()-1));
        }else if(sequence<=train.getNubberOfCars()) {
            tmpStr ="Вагон успішно видалено. Інформація про вагон:\n"+train.delCar(sequence);
            System.out.println(tmpStr);
            actionsFile.addToFile(tmpStr);
        }
        else
            System.out.println("Ви неправильно ввели порядковий номер, він не може бути меншим за 1");
    }

    public void showTrainWithNumber(){
        if(!trains.isEmpty()) {
            tmpStr = "Знайдено вагон " + findTrainWithNumber();
            System.out.println(tmpStr);
            actionsFile.addToFile(tmpStr);
        }
        else
            System.out.println("У списку немає поїздів");
    }

    public static Train findTrainWithNumber(){
        System.out.println("Введіть номер поїзда для пошуку:");
        int number = obj.nextInt();
        obj.nextLine();
        if(trains.get(number) == null) {
            System.out.println("Ви неправильно ввели номер поїзда");
            findCarWithNumber();
        }
        return trains.get(number);
    }

    public void showTrainWythSortedCarsByComfort(){
        Train train = findTrainWithNumber();
        System.out.println("Відсортовані вагони за кількістю пасажирів");
        actionsFile.addToFile("Відсортовані вагони за кількістю пасажирів з поїзда номер:" + train.getTrainNumber());
        for(Car car: train.getSortCarsByPasangerNumber()){
            System.out.println(car);

        }
    }

    public void closeFileWriters(){
        actionsFile.closeWriter();
        trainsFile.closeWriter();
        carsFile.closeWriter();
    }

    public void showFileInfo(){
        System.out.println("Дані про вагони");
        OutputFromFile carsOutput = new OutputFromFile("cars");
        carsOutput.outputFromFile();
        System.out.println("Дані про поїзди");
        OutputFromFile trainsOutput = new OutputFromFile("trains");
        trainsOutput.outputFromFile();
        System.out.println("Дані про дії");
        OutputFromFile actionsOutput = new OutputFromFile("actions");
        actionsOutput.outputFromFile();

        carsOutput.closeWriter();
        trainsOutput.closeWriter();
        actionsOutput.closeWriter();
    }

}





