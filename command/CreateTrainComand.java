package command;

import menu.TrainService;

public class CreateTrainComand implements Command{
    TrainService service = new TrainService();

    public CreateTrainComand(TrainService service){
        this.service = service;
    }

    @Override
    public void executeMenu(TrainService service) {
        TrainService.createTrain();
    }

}
