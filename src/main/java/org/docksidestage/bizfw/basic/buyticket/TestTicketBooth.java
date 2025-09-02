package org.docksidestage.bizfw.basic.buyticket;

// TODO hase 厳密には、Test...クラスは、src/test/java にあった方が良い by jflute (2025/09/02)
// そうすると、チケットのユーザーさん (のプログラム) は、これを利用することができない。
/**
 * テスト用チケットブースのクラス。
 * テスト環境でのチケット購入処理を行う。
 * @author tahasega
 */
public class TestTicketBooth extends TicketBooth {
    
    // ===================================================================================
    //                                                                           Attribute
    //                                                                          ==========
    // TODO hase 固定だと夜のテストとかしづらいので、newする側から受け取るほうがいいかなと by jflute (2025/09/02)
    // 隠蔽するという意味では、別に書いたようにsrc/test/javaに配置すれば良い。
    // (し、一般ユーザーは、普通のTicketBoothで買うのでTestは使わないだろうし)
    private static final int testHour = 10;
    // done hase step6のFactoryを思い出せ by jflute (2025/08/19)
    
    // TestTicketBoothクラスを作るか、TicketBoothクラスをテスト用に拡張するか悩みました。by hase (2025/08/18)
    // TestTicketクラスも作成してあったので、テスト用のboothがあってもいいと判断しました。
    // ===================================================================================
    //                                                                            Override
    //                                                                            ========
    // TODO hase 現時点でもすでに、createTicket()をオーバーライドする必要性がない by jflute (2025/09/02)
    // (TicketBooth側でcreateTicket()はどのみちあってもいいけど)
    // 単純に ticketType.set をするタイミングをcreateTicket()にしただけなので...
    // 例えば、new TestTicketBooth() (コンストラクター) をした時点で set しちゃうとか。
    //
    // まあ、TestTicketを確保するスタイルなのであれば、enumのsetのタイミングどうのこうの関係なく、
    // このcreateTicket()はあってもいいかな。
    @Override
    protected Ticket createTicket(TicketType ticketType) {
        ticketType.setCurrentHourSupplier(() -> testHour); // テスト用時刻を注入
        return new TestTicket(ticketType);
    }
}
