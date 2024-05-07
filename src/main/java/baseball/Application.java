package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;


public class Application {

    // 3자리 숫자 만들기
    static String NewNumber() {
        int a,b,c;
        do {
            a = Randoms.pickNumberInRange(1, 9);
            b = Randoms.pickNumberInRange(1, 9);
            c = Randoms.pickNumberInRange(1, 9);
        } while (a == b || b == c || c == a);
        return Integer.toString(a) + Integer.toString(b) + Integer.toString(c);
    }

    // 숫자 비교
    static boolean CompareNumber(String number, String input) {

        int same = 0;
        // 동일한 숫자가 몇 개 있는지
        for (int i = 0; i < 3; i++) {
            char c = number.charAt(i);
            if (input.indexOf(c) != -1) {
                same++;
            }
        }

        int strike = 0;
        for (int i = 0; i < 3; i++){
            // 같은 수가 같은 자리면 스트라이크 + 1
            if (number.charAt(i) == input.charAt(i))
                strike++;
        }

        if (strike == 3) {
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return false;
        }

        // 같은 숫자에서 스트라이크 빼면 볼
        int ball = same - strike;

        if (same == 0) {
            System.out.println("낫싱");
        } else if (strike == 0) {
            System.out.println(ball + "볼");
        } else if (ball == 0) {
            System.out.println(strike + "스트라이크");
        } else {
            System.out.println(ball + "볼 " + strike + "스트라이크");
        }
        return true;
    }

    public static void main(String[] args) {
        do {
            String number = NewNumber();
            String input;

            do {
                System.out.print("숫자를 입력하세요 : ");
                input = Console.readLine();
            } while (CompareNumber(input, number));

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        } while (Console.readLine().equals("1"));
    }
}
