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
// #1on1: すでにTestTicketの固有の処理がないけど、これはこれで将来のためにあっても良い (2025/09/02)
//    private static final int testHour = 16;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * チケット種別に応じてチケットを作成するコンストラクタ。
     * @param ticketType チケット種別
     */
    // もはや独自の処理や変数は無くなってしまったが、将来のテスト拡張のために残しておきます。by hase (2025/08/20)
    public TestTicket(TicketType ticketType) {
        super(ticketType);
    }

    // ===================================================================================
    //                                                                            Override
    //                                                                            ========
// done hase TicketTypeにisEntryAvailableメソッドを移したので、このオーバーライドが機能しなくなった。 by hase (2025/08/19)
//
//    @Override
//    protected int getCurrentHour() {
//        return testHour; // ここの値を変えてテストする
//    }
}
