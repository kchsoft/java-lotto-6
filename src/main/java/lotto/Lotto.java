package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public Integer getNumber(Integer index) {
        return numbers.get(index);
    }

    public Integer getSize(){
        return numbers.size();
    }

    public boolean contains(Integer number) {
        return numbers.contains(number);
    }

}
