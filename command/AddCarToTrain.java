package command;

import menu.TrainService;

public class AddCarToTrain implements Command{

    TrainService service = new TrainService();

    public AddCarToTrain(TrainService service){
        this.service = service;
    }

    @Override
    public void executeMenu(TrainService service) {
        service.addCarToTrain();
    }

}
