package org.docksidestage.bizfw.basic.buyticket;

// TODO hase 細かいですが、javadocお願いします by jflute (2025/07/02)
// // 3. 最低限のクラスJavaDoc
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
public class TicketBuyResult {
    private Ticket result = null; // 購入したチケット
    private Integer change = null; // お釣り

    public TicketBuyResult(Integer handedMoney, Integer NDaysPrice) {
        if (handedMoney - NDaysPrice >= 0) {
            this.result = new Ticket(NDaysPrice);
            this.change = handedMoney - NDaysPrice;
        }
    }

    public Ticket getTicket() {
        return result;
    }

    public Integer getChange() {
        return change;
    }
}
