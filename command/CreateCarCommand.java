package command;

import menu.TrainService;

public class CreateCarCommand implements Command{
    TrainService service = new TrainService();

    public CreateCarCommand(TrainService service){
        this.service = service;
    }

    @SuppressWarnings("static-access")
	@Override
    public void executeMenu(TrainService service) {
        service.createCar();
    }

}
