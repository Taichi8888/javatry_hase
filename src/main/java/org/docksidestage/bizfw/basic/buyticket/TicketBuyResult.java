package org.docksidestage.bizfw.basic.buyticket;

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
