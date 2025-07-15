/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

// done hase 細かいですが、@authorは2行目を追加しちゃってOKです by jflute (2025/07/02)
// e.g.
//  @author jflute
//  @author tahasega
/**
 * @author jflute
 * @author tahasega
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    public static final int MAX_QUANTITY = 10;
// おもいで：TicketTypeでのみPriceを参照しているので、Price単体の変数は削除しました by hase (2025/07/08)
//    public static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
//    public static final int TWO_DAY_PRICE = 13200;
//    public static final int FOUR_DAY_PRICE = 22400;
//    public static final int TWO_NIGHT_PRICE = 7400;
    // done hase 修行++: enum という文法を調べて使ってみてください。 by jflute (2025/07/09)
// おもいで：enumを知った日 by hase (2025/07/09)
// enumは定数の集合を型安全に表現できるクラス。
//    public static final TicketType ONE_DAY_TICKET = new TicketType(7400, 1, false);
//    public static final TicketType TWO_DAY_TICKET = new TicketType(13200, 2, false);
//    public static final TicketType FOUR_DAY_TICKET = new TicketType(22400, 4, false);
//    public static final TicketType TWO_NIGHT_TICKET = new TicketType(7400, 2, true);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private int salesProceeds; // Not null
    // done hase 一回購入処理の中で発生する一時的なお釣りという値をインスタンス変数にすると... by jflute (2025/07/07)
    // 同じインスタンスで同時にアクセスされたときに、このchangeの値が入れ違ってしまう可能性があります。
    // あまり同時に処理を受け付けるつもりがないクラスだとしても、むやみにインスタンスに一時的な値を入れない方が無難です。
    // getChange()のためにそうしたのかなと思ったのですが...getChange()誰も使ってないような？？？
// おもいで by hase (2025/07/08)
    // private int change;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * ⚪︎Dayパスポートを買う、パークゲスト用のメソッド。（1,2,4daysと2nights）
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // done hase [いいね] NDay でうまく再利用できましたね！ by jflute (2025/07/02)
    // done hase JavaDocが、one-dayのままです by jflute (2025/07/02)
    // チーム開発で非常に大事ですね、気をつけます。

    // done hase 多少個人差もありますが、Javaだと基本publicは上で、privateが下になります by jflute (2025/07/02)
    // 上に定義しているpublicのメソッドが、下に定義しているprivateのものを呼ぶみたいな。
    // 合わせて頂けるとありがたいというところではあります。
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
        // done hase [いいね] だいぶ綺麗になって、新しいチケット種別が来ても、追加がすごく簡単になりましたね！ by jflute (2025/07/07)
        // done hase 一方で、priceとdaysと夜か？の三つの情報で、一つのチケット種別という業務概念に捉えることができそうです by jflute (2025/07/07)
        // Testクラスの showTicketIfNeeds() で書いたtodoとつながってきます。
        return doBuyTicket(handedMoney, TicketType.ONE_DAY);
    }

    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        return doBuyTicket(handedMoney, TicketType.TWO_DAY);
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        return doBuyTicket(handedMoney, TicketType.FOUR_DAY);
    }

    public TicketBuyResult buyTwoNightPassport(Integer handedMoney) {
        return doBuyTicket(handedMoney, TicketType.TWO_NIGHT);
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // done hase かなり小テクニックですが、privateの「実処理」をするメソッドを作るとき、先頭文字を変えるのをよく見かけます by jflute (2025/07/07)
    // publicのbuyTwoDayPassport()に対して、今のbuyNDayPassport()だと、メソッド補完したときに似た名前が並んで視認しづらいんですよね。
    // なので、publicは一番大事なので一番普通のbuy...を使うとして、実処理のprivateは例えば doBuy...() とか。実際に買うみたいなニュアンス。
    // 他にも internalBuy...() とか世の中色々とあります。ぼくは意味的にもしっくり来て短いので do をよく使っています。
    // 確かに名前が似ていると読みづらいですね...自分もdoつけてみます！(by hase 25/7/7)
    //
    // done hase javadoc, せっかくなので、@param で NDayPrice も追加しましょう by jflute (2025/07/02)
    // ここはTicketBoothにおけるとても重要なメソッドなので、JavaDocの費用対効果も高いです。←JavaDocの費用対効果...意識してみます！(hase)
    // done hase えらく細かいですが、Javaの引数名は先頭小文字が週間なので、nDayPriceの方がいいかなと by jflute (2025/07/02)
    // せっかくなので、IDEのリファクタリング機能を使って1箇所だけ直してOKの簡単にrename処理してみましょう。←すごい！！night ticketもあるのでticketPriceにしました(hase)
    // done hase javadoc, @paramが足りてません by jflute (2025/07/07)
    // done hase javadoc, @param ticketPrice, javatryではぜひ (NotNull) を付けるようにお願いします。handedMoney参考に by jflute (2025/07/07)
    // nullを許す引数なのかどうか？というのは呼び出し側にとって重要な情報なので、javadocにこそ書いてあると呼び出す人は助かります。
    /**
     * Buy N-day / M-night passport, method for park guest. (N = 1,2,4) (M = 2)
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
// おもいで：price, days, onlyNightをticketで管理していた時代
//     * @param ticketPrice 各チケットの料金 (NotNull, NotMinus)
//     * @param nDays チケットの残り日数 (NotNull, NotMinus)
//     * @param onlyNightAvailable 夜間のみ入園可能なチケットか (True: 夜間のみ入園可能, False: 昼間も入園可能)
     * @param ticketType 購入するチケットの種類 (NotNull)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    private TicketBuyResult doBuyTicket(Integer handedMoney, TicketType ticketType) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        } // 売り切れ
        // done hase ここ少しコメントの補足欲しいですね。マイナスpriceだったら、プラスに戻してる？のはなぜ？ by jflute (2025/07/02)
        // 例えば、handedMoneyもデータ型的にはマイナス入りますが、仕様としてJavaDocにNotMinusと書いてあります。
        // 実際にチェックして例外出すの方が理想ですが、一応業務的にマイナスは許さないよという思想という感じではあります。
        // 今の実装だと、NDayPriceは「マイナス入れられてもプラスに補正して動くよ」って感じに見えます。
        //
        // (続き) ↑って書いた後に、「夜間は負の数で管理する」というコメントを見つけました(^^。
        // なるほどぅ。ただ、あまりマイナスに業務的な意味を保たせて制御するというのは、あまり好まれない方法です。
        // マジックナンバーに近い使い方なので、将来本当にマイナスを業務的に表現する必要が出てきたときに破綻します。
        // 夜間であることは、別途何かしらの(直接的な)方法で表現した方が良いかなとは思いました。
        //        if (handedMoney < ticketPrice) {
        //            throw new TicketShortMoneyException("Short money: " + handedMoney);
        //        } // TicketBuyResultでチェックするように変更しました by hase (2025/07/02)
        // done hase --quantity; は、if-else の外に出しても良いのでは？ by jflute (2025/07/02)
        // 本当だ...ありがとうございます
        // done hase Resultの方のtodoを書いてからこっちを書いてるのですが...ShortMoneyのチェックが移動したんですね by jflute (2025/07/07)
        // 購入で必要なチェックは最初にまとまっていることを期待してしまうので、分かれると「あれ？お金足りないときは？」って一瞬思ってしまいます。
        // もちろんShortMoneyはチケットを作って提供する「Result」の役割ということであれば腑に落ちる部分もありますが...
        // 「チケットを作って提供する」って業務を行うクラスとしてはResultってのがちょっと合ってないのかなと思いました。
        // 例えば、PurchasedTicketPresenter ってクラスを新たに作って、そいつが new Ticket() して TicketBuyResult を戻すとか。
        // この場合、TicketBuyResult は単なる入れ物クラスにして、お金不足のチェックやチケット発行処理は Presenter がやるとか。
        // みたいな感じであれば、チェックが分かれてても、それぞれの業務クラスでチェックしてるってことで直感的かなと。

        // なるほど...「直感的」なコードを書くのが苦手みたいです。(by hase 25/7/7)
        // チェックが分かれているのは、やはりややこしく思えてきたので、購入時のチェックやチケット発行もTicketBoothの役割とし、
        // TicketBuyResultは単なる入れ物クラスにしたいと思います。
        // そして、doBuyNDayPassport()の戻り値をTicketではなくTicketBuyResultに変更し、購入処理の結果からチケットとお釣りを取得できるようにします。
        if (handedMoney - ticketType.getTicketPrice() < 0) {
            throw new TicketBooth.TicketShortMoneyException("Short money: " + handedMoney);
        } // 金額不足
        Ticket ticket = new Ticket(ticketType);
        int change = handedMoney - ticket.getTicketPrice(); // お釣り（>= 0）
        TicketBuyResult result = new TicketBuyResult(ticket, change);
        // done jflute (質問です) 以下の売上では、salesProceedsを0で初期化すればif文が要らないと思うのですが、Noneの方が適切でしょうか？
        // （未購入状態と売上0を違うものとして認識する時が想定できず質問しました） by hase (2025/07/07)
        // done hase 0初期化で良いと思います。既存コードあえてパーフェクトにしてないので、よく思いつきましたという感じで^^ by jflute (2025/07/07)
        // まあ、例えば0円商品があったとき、購入が全くない0円と、売上があっても0円で区別が付かないとかはなくはないですが、
        // その状況は相当レアではありますし、購入の有無が必要な業務であれば別途変数を持たせても良いと思うので。
        //
        // ちなみに、todoは、4文字ぜんぶ大文字でお願いします。エディター的に大文字4文字のtodoが色が付いて認識されるというものなので。
        // 承知しました！自分のエディターの設定も変えたので、今後は大文字じゃなければ自分で気づけます！ by hase (2025/07/08)
        salesProceeds += ticket.getTicketPrice(); // おもいで：Not nullにしたからシンプルに書けた！！ by hase (2025/07/08)
        --quantity;
        return result;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }

}
