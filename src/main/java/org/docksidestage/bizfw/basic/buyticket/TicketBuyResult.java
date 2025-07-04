package org.docksidestage.bizfw.basic.buyticket;

// done hase 細かいですが、javadocお願いします by jflute (2025/07/02)
// // 3. 最低限のクラスJavaDoc
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * チケット購入結果（購入したチケットと購入時のお釣り）
 * @author tahasega
 */
public class TicketBuyResult {
    // TODO done hase [いいね]こういう横のスラスラ(//)コメントの補足嬉しいですね！ by jflute (2025/07/02)
    private final Ticket ticket; // 購入したチケット
    private final Integer change; // お釣り

    public TicketBuyResult(Integer handedMoney, Integer nDayPrice, Integer nDays, Boolean onlyNightAvailable) {
        // TODO done hase 矛盾した引数を入れられた場合、もう例外throwで弾いてしまった良いかなとは思います by jflute (2025/07/02)
        // 今だと、ticket/changeがnullのままになって、getの呼び出し先でNullPointerになるとか事象がわかりづらくなるかなと。
        if (handedMoney - nDayPrice < 0) {
            throw new TicketBooth.TicketShortMoneyException("Short money: " + handedMoney);
        }
        this.ticket = new Ticket(nDayPrice, nDays, onlyNightAvailable);
        this.change = handedMoney - nDayPrice;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Integer getChange() {
        return change;
    }
}
