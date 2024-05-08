package baseball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// static을 붙이면 클래스명 없이 함수 바로 사용가능
import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class Computer {

    // 굳이? 이런걸 채용하는 사람 입장에서 좋아하는지..
    public static final int NUMBER_DIGITS = 3;
    public static final int MAX_NUMBER = 9;
    public static final int MIN_NUMBER = 1;


    private static final boolean VALID_SUCCESS = true;
    private static final boolean VALID_FAILED = false;

    // 이건 굳이?
    private List<Integer> randomNumbers;


    public Computer() {
    }

    public void init() {
        do {
            generateNewRandomNumbers();
        } while (validateNumbers(randomNumbers) == VALID_FAILED);
        // 굳이 while로 확인하는 이유
    }

    private void generateNewRandomNumbers() {
        randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < NUMBER_DIGITS) {
            int number = pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
            if (!randomNumbers.contains(number)) {
                randomNumbers.add(number);
            }
        }
    }


    // 숫자가 유효한 숫자인지 검사하는 함수
    private boolean validateNumbers(List<Integer> numbers) {

        // 숫자가 세자리가 아닐 경우
        if (numbers.size() != NUMBER_DIGITS) {
            return VALID_FAILED;
        }

        // 숫자가 1이상, 10이하가 아닐 경우
        for (int i = 0; i < NUMBER_DIGITS; i++) {
            if (numbers.get(i) < MIN_NUMBER || numbers.get(i) > MAX_NUMBER)
                return VALID_FAILED;
        }

        // 숫자 중복되는지 확인
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            return VALID_FAILED;
        }

        return VALID_SUCCESS;
    }


    // strike -> 위치랑 숫자가 둘 다 일치하면 count++
    public int checkStrikeCount(List<Integer> userNumbers) {
        int count = 0;

        for (int i = 0; i<NUMBER_DIGITS; i++) {
            if (userNumbers.get(i) == randomNumbers.get(i))
                count++;
        }

        return count;
    }


    // ball -> 존재하기만 하면 count++
    public int checkBallCount(List<Integer> userNumbers) {
        int count = 0;
        for (Integer userNumber : userNumbers) {
            if (randomNumbers.contains(userNumber))
                count++;
        }
        return count;
    }
}