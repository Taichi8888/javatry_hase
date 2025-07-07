package org.docksidestage.bizfw.basic.buyticket;

// done hase 細かいですが、javadocお願いします by jflute (2025/07/02)
// // 3. 最低限のクラスJavaDoc
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * チケット購入結果（購入したチケットと購入時のお釣り）を保持する
 * @author tahasega
 */
public class TicketBuyResult {
    // done hase [いいね]こういう横のスラスラ(//)コメントの補足嬉しいですね！ by jflute (2025/07/02)
    private final Ticket ticket; // 購入したチケット
    private final Integer change; // お釣り

    public TicketBuyResult(Ticket ticket, int change) {
//        // done hase 矛盾した引数を入れられた場合、もう例外throwで弾いてしまった良いかなとは思います by jflute (2025/07/02)
//        // 今だと、ticket/changeがnullのままになって、getの呼び出し先でNullPointerになるとか事象がわかりづらくなるかなと。
//        if (handedMoney - ticketPrice < 0) {
//            // TODO done hase [いいね] ShortMoney例外使うの良いですね！ by jflute (2025/07/07)
//            throw new TicketBooth.TicketShortMoneyException("Short money: " + handedMoney);
//        }
//        // TODO done hase [ふぉろー] ここはコメント程度で。Constructorでnew Ticketするか？Ticketを受け取るか？ by jflute (2025/07/07)
//        // この辺は人によって若干好みが変わりそうです。
//        // Constructorって (変数やメソッドに比べて) Javaの中ではちょっとレアな登場人物で...
//        // あんまり Constructor であれこれ処理が入ってることを想定しない意識で読む人も多いので見逃されちゃいがちで...
//        // (ケースバイケースですが)個人的にはできるだけ避けようとはしています。
//        this.ticket = new Ticket(ticketPrice, nDays, onlyNightAvailable);
//        // TODO done hase ↑と似た話ですが、「お釣りを計算する」というのも小さいながらも立派な業務ロジックです。 by jflute (2025/07/07)
//        // 業務ロジックが、いかにも入れ物クラスっぽいResultクラスの(しかも)Constructorで実装されていると、探すの大変そうだなと。
//        this.change = handedMoney - ticketPrice;
        this.ticket = ticket;
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    } // チケットを取得

    public Integer getChange() {
        return change;
    } // お釣りを取得
}
