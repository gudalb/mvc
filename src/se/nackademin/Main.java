package se.nackademin;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int gameSize = 15;

        View view = new View(gameSize);
        view.setVisible(true);

        Model model = new Model(gameSize);

        Controller controller = new Controller(model, view);
        Thread t1 = new Thread(controller);
        t1.start();



    }
}
