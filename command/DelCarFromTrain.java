package command;

import menu.TrainService;

public class DelCarFromTrain implements Command{

    TrainService service = new TrainService();

    public DelCarFromTrain(TrainService service){
        this.service = service;
    }

    @Override
    public void executeMenu(TrainService service) {
        service.delCarFromTrain();
    }

}
