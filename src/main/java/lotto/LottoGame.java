package lotto;

public class LottoGame {

    public LottoGame(){
    }

    public void startProcess(){
        int money = -1;
        while(money < 0){
            GuideMessage.ofInputMoney();
            money = GameInput.insertMoney();
        }
    }
}
