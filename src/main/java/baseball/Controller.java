package baseball;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Controller {
    private static final String GAME_START = "1";
    private static final String GAME_END = "2";

    // 디폴트 생성자 굳이 써주는 이유?
    // 명시적으로 써주는게 좋음
    public Controller() {
    }

    public void start() {
        // 시작 상태를 표현할 변수 필요
        String state = GAME_START; // 1로 표현하면 가독성 안좋으니 더 직관적으로 표현
        Computer computer = new Computer();
        Game game = new Game(computer);
        while (state.equals(GAME_START)) {
            game.init();
            game.play();

            // 게임이 끝나면 재시작 요청 메세지
            state = Restart();
        }
    }

    private String Restart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = readLine();
        validateInput(input); // 1이나 2가 아니면 오류 발생
        return input;
    }

    private void validateInput(String input) {
        if (!input.equals(GAME_START) && !input.equals(GAME_END))
            throw new IllegalArgumentException("the input is not 1 or 2");
    }
}
