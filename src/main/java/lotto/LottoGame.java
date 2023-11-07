package lotto;

public class LottoGame {

    public LottoGame(){
    }

    public void startProcess() {
        Integer money = -1;
        while (money < 0) {
            GuideMessage.ofInputMoney();
            money = GameInput.insertMoney();
        }
        Lottos lottos = Lottos.issueLottos(money);
        Integer issueNumber = lottos.getSize();
        GuideMessage.ofIssuedLottos(lottos, issueNumber);

        GuideMessage.ofInputWinningNumber();
        Lotto winnigNumbers = GameInput.insertWinnigNumbers();

        GuideMessage.ofInputBounsNumber();
        Integer bonusNumber = GameInput.insertBonusNumber();

        LottoResultWinners lottoResultWinners = findWinnerOfLotto(lottos,winnigNumbers,bonusNumber);
        Integer sumOfPrizeMoney = findSumOfPrizeMoney(lottoResultWinners);
        Double earningRate = getEarningRate(money,sumOfPrizeMoney);
        GuideMessage.ofLottoWinnerResult(lottoResultWinners,earningRate);

    }

    public Integer findSumOfPrizeMoney(LottoResultWinners lottoResultWinners){
        Integer sum = 0;
        for (Rank rank : Rank.values()) {
            if (lottoResultWinners.getNumberOfWinner(rank) == 0){
                continue;
            }
            sum += Integer.valueOf(Converter.convertCommaStringToString(rank.getPrizeMoney()))
            * lottoResultWinners.getNumberOfWinner(rank);
        }
        return sum;
    }


    public LottoResultWinners findWinnerOfLotto(Lottos lottos, Lotto winningNumbers, Integer bonusNumber) {
        LottoResultWinners lottoResultWinners = new LottoResultWinners();
        for (Integer lottoIndex = 0; lottoIndex < lottos.getSize(); lottoIndex++) {
            Lotto lotto = lottos.getLotto(lottoIndex);
            LottoResult lottoResult = compareLottoToWinningNumbers(lotto,winningNumbers,bonusNumber);
            lottoResultWinners.add(lottoResult);
        }
        return lottoResultWinners;
    }

    public LottoResult compareLottoToWinningNumbers(Lotto lotto,Lotto winningNumbers,Integer bonusNumber){
        Integer numberOfMatch = 0;
        Boolean bonus = false;
        for (Integer numberIndex = 0; numberIndex < lotto.getSize(); numberIndex++) {
            if (lotto.contains(winningNumbers.getNumber(numberIndex))) {
                numberOfMatch++;
            }
            if(lotto.contains(bonusNumber)){
                bonus = true;
            }
        }
        return new LottoResult(numberOfMatch,bonus);
    }

    public Double getEarningRate(Integer money,Integer sumOfPrizeMoney) {
        Double earningRate = Double.valueOf(sumOfPrizeMoney)  / Double.valueOf(money)  * 100;
        return Double.valueOf(Math.round(earningRate * 10)) / 10;
    }
}
