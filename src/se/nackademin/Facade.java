package se.nackademin;

import se.nackademin.Controller.Controller;
import se.nackademin.Model.Model;
import se.nackademin.View.View;

public class Facade {

    void startGame () {
        int gameSize = 15;
        View view = new View(gameSize);
        Model model = new Model(gameSize);
        model.addPropertyChangeListener(view);
        Controller controller = new Controller(model, view);
        Thread t1 = new Thread(controller);
        t1.start();
    }

}
