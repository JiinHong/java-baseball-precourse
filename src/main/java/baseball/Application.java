package baseball;

public class Application {
    public static void main(String[] args) {

        // 굳이 컨트롤러를 쓰는 이유?
        Controller controller = new Controller();
        controller.start();
    }
}
