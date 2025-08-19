package org.docksidestage.bizfw.basic.buyticket;

/**
 * テスト用チケットブースのクラス。
 * テスト環境でのチケット購入処理を行う。
 * @author tahasega
 */
public class TestTicketBooth extends TicketBooth {
    
    // TODO hase step6のFactoryを思い出せ by jflute (2025/08/19)
    
    // TestTicketBoothクラスを作るか、TicketBoothクラスをテスト用に拡張するか悩みました。by hase (2025/08/18)
    // TestTicketクラスも作成してあったので、テスト用のboothがあってもいいと判断しました。
    public TicketBuyResult doBuyTicket(int handedMoney, TicketType ticketType) {
        if (getQuantity() <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney - ticketType.getTicketPrice() < 0) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        TestTicket testTicket = new TestTicket(ticketType);
        int change = handedMoney - testTicket.getTicketPrice();
        TicketBuyResult result = new TicketBuyResult(testTicket, change);
        addSalesProceeds(testTicket.getTicketPrice());
        decreaseQuantity();
        return result;
    }
}
