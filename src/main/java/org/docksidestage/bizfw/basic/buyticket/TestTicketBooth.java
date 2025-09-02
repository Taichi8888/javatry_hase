package org.docksidestage.bizfw.basic.buyticket;

/**
 * テスト用チケットブースのクラス。
 * テスト環境でのチケット購入処理を行う。
 * @author tahasega
 */
public class TestTicketBooth extends TicketBooth {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                          ==========
    private static final int testHour = 10;
    // TODO done hase step6のFactoryを思い出せ by jflute (2025/08/19)
    
    // TestTicketBoothクラスを作るか、TicketBoothクラスをテスト用に拡張するか悩みました。by hase (2025/08/18)
    // TestTicketクラスも作成してあったので、テスト用のboothがあってもいいと判断しました。
    // ===================================================================================
    //                                                                            Override
    //                                                                            ========
    @Override
    protected Ticket createTicket(TicketType ticketType) {
        ticketType.setCurrentHourSupplier(() -> testHour); // テスト用時刻を注入
        return new TestTicket(ticketType);
    }
}
