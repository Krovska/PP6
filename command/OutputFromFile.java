package command;

import menu.TrainService;

public class OutputFromFile implements Command{

    TrainService service = new TrainService();

    public OutputFromFile(TrainService service){
        this.service = service;
    }

    @Override
    public void executeMenu(TrainService service) {
        service.showFileInfo();
    }
}
