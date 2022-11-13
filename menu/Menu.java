package menu;

import command.*;

import java.util.Scanner;

public class Menu {
    private static TrainService service = new TrainService();
    Scanner obj = new Scanner(System.in);

    public int menu(){
        Receiver receiver = addAllCommand();
        System.out.println("Виберіть пункт меню:\n1 - Добавити новий вагон до списку \n2 - Створити новий поїзд \n3 - Додати вагон у певний поїзд \n4 - Видалити вагон з певного поїзда \n5 - Вивести інформацію про певний вагон \n6 - Вивести інформацію про поїзд за номером \n7 - Вивести інформацію про поїзд за певними параметрами \n8 - Вивести інформацію з файлів\n9 - Завершити роботу");
        int commandIndex = obj.nextInt();
        obj.nextLine();
        if (commandIndex==9)
            return 0;
        receiver.runCommand(commandIndex-1);
        service.showFileInfo();
        this.menu();

        return 0;
    }
    public static Receiver addAllCommand(){
        Receiver receiver = new Receiver();
        receiver.addCommand(new CreateCarCommand(service));
        receiver.addCommand(new CreateTrainComand(service));
        receiver.addCommand(new AddCarToTrain(service));
        receiver.addCommand(new DelCarFromTrain(service));
        receiver.addCommand(new ShowCarWithNumber(service));
        receiver.addCommand(new ShowTrainWithNumber(service));
        receiver.addCommand(new ShowTrainWithParameters(service));
        receiver.addCommand(new OutputFromFile(service));

        return receiver;
    }
}
