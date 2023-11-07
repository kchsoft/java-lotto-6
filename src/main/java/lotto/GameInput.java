package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Collections;
import java.util.List;

public class GameInput {

    private static final int INPUT_FAIL = -1;

    public static int insertMoney(){ // int -> Integer , INPUT FAIL == null
        String money = Console.readLine();
        try{
            IntegerValidator.isZeroOrPositiveInteger(money);
        }
        catch (IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException.getMessage());
            return INPUT_FAIL;
        }
        return Integer.valueOf(money);
    }

    public static Lotto insertWinnigNumbers(){
        String commaWinnigNumbers = Console.readLine();
        try {
            IntegerValidator.checkWinnigNumbersValue(commaWinnigNumbers);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return null;
        }
        List<Integer> winningNumbers = Converter.convertCommaStringToListInt(commaWinnigNumbers);
        Collections.sort(winningNumbers);
        return new Lotto(Collections.unmodifiableList(winningNumbers));
    }

    public static Integer insertBonusNumber(){
        String number = Console.readLine();
        try {
            IntegerValidator.checkBonusNumberValue(number);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return null;
        }
        return Integer.valueOf(number);
    }

}
