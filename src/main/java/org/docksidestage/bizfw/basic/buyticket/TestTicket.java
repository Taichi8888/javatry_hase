package org.docksidestage.bizfw.basic.buyticket;

/**
 * 入園チケットをテストするためのクラス。
 * チケット種別、入園可能日数、使用済みかどうかなどを管理し、入園処理も行う。
 * @author tahasega
 */
public class TestTicket extends Ticket {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                          ==========
    private static final int testHour = 16;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * チケット種別に応じてチケットを作成するコンストラクタ。
     * @param ticketType チケット種別
     */
    public TestTicket(TicketType ticketType) {
        super(ticketType);
    }

    // ===================================================================================
    //                                                                            Override
    //                                                                            ========
    @Override
    protected int getCurrentHour() {
        return testHour; // ここの値を変えてテストする
    }
}
