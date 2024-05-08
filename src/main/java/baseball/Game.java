package baseball;

import static baseball.Computer.*;
import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.*;

public class Game {
    private Computer computer;
    private int strike;
    private int ball;

    public Game(Computer computer) {
        this.computer = computer;
        this.strike = 0;
        this.ball = 0;
    }

    public void init() {
        computer.init();
        strike = 0;
        ball = 0;
    }


    // 게임 시작
    // 숫자 입력 -> 숫자 유효성 검사 -> strike, ball -> 성공 메세지
    public void play() {
        List<Integer> userNumbers;
        while (strike < NUMBER_DIGITS) {
            System.out.print("숫자를 입력해주세요 : ");
            String userInput = readLine();
            // 숫자인지 체크
            isNumber(userInput);

            userNumbers = StringToNumber(userInput);
            sizeCheck(userNumbers);
            valueCheck(userNumbers);
            duplicateCheck(userNumbers);

            strike = computer.checkStrikeCount(userNumbers);
            ball = computer.checkBallCount(userNumbers);

            printHint();
        }
        SuccessMessage();
    }

    private void SuccessMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    private void printHint() {
        if (strike == NUMBER_DIGITS) {
            System.out.println("3스트라이크");
        } else if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
        } else if (strike != 0 && ball == 0) {
            System.out.println(strike + "스트라이크");
        } else if (strike == 0 && ball != 0) {
            System.out.println(ball + "볼");
        } else if (strike != 0 && ball != 0){
            System.out.println(ball + "볼 " + strike + "스트라이크");
        }
    }

    // 숫자인지 체크
    private void isNumber(String input) {
        if (!input.matches("\\d+"))
            throw new IllegalArgumentException("the input is not a Integer.");
    }

    // 스트링으로 된 입력을 리스트로 변경
    private List<Integer> StringToNumber(String str) {
        List<Integer> list = new ArrayList<>(); // 리턴해줄 list 생성
        for (int i = 0; i<str.length(); i++) {
            list.add(Integer.parseInt(Character.toString(str.charAt(i))));
        }
        return list;
    }

    // 세자리인지 체크
    private void sizeCheck(List<Integer> list) {
        if (list.size() != NUMBER_DIGITS)
            throw new IllegalArgumentException("the input is not a Integar.");
    }

    // 각 숫자가 1이상 9이하인지 체크
    private void valueCheck(List<Integer> list) {
        for (Integer i : list) {
            if (i<1 || i>9)
                throw new IllegalArgumentException("the input is not a Integar.");
        }
    }

    // 숫자 중복되는지 체크
    private void duplicateCheck(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        if (set.size() != list.size())
            throw new IllegalArgumentException("the input is not a Integar.");
    }
}

