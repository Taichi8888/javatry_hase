package org.docksidestage.bizfw.basic.buyticket;

// TODO done hase 細かいですが、javadocお願いします by jflute (2025/07/02)
// // 3. 最低限のクラスJavaDoc
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * チケット購入結果（購入したチケットと購入時のお釣り）
 * @author tahasega
 */
public class TicketBuyResult {
    private Ticket ticket = null; // 購入したチケット
    private Integer change = null; // お釣り

    public TicketBuyResult(Integer handedMoney, Integer NDaysPrice) {
        if (handedMoney - NDaysPrice >= 0) {
            this.ticket = new Ticket(NDaysPrice);
            this.change = handedMoney - NDaysPrice;
        }
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Integer getChange() {
        return change;
    }
}
