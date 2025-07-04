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
    public static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    public static final int TWO_DAY_PRICE = 13200;
    public static final int FOUR_DAY_PRICE = 22400;
    public static final int TWO_NIGHT_PRICE = 7400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase
    private int change;

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

    // TODO done hase 多少個人差もありますが、Javaだと基本publicは上で、privateが下になります by jflute (2025/07/02)
    // 上に定義しているpublicのメソッドが、下に定義しているprivateのものを呼ぶみたいな。
    // 合わせて頂けるとありがたいというところではあります。
    public Ticket buyOneDayPassport(Integer handedMoney) {
        return buyNDayPassport(handedMoney, ONE_DAY_PRICE, 1, false);
    }

    public Ticket buyTwoDayPassport(Integer handedMoney) {
        return buyNDayPassport(handedMoney, TWO_DAY_PRICE, 2, false);
    }

    public Ticket buyFourDayPassport(Integer handedMoney) {
        return buyNDayPassport(handedMoney, FOUR_DAY_PRICE, 4, false);
    }

    public Ticket buyTwoNightPassport(Integer handedMoney) {
        return buyNDayPassport(handedMoney, TWO_NIGHT_PRICE, 2, true);
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

    // TODO done hase javadoc, せっかくなので、@param で NDayPrice も追加しましょう by jflute (2025/07/02)
    // ここはTicketBoothにおけるとても重要なメソッドなので、JavaDocの費用対効果も高いです。←JavaDocの費用対効果...意識してみます！(hase)
    // TODO done hase えらく細かいですが、Javaの引数名は先頭小文字が週間なので、nDayPriceの方がいいかなと by jflute (2025/07/02)
    // せっかくなので、IDEのリファクタリング機能を使って1箇所だけ直してOKの簡単にrename処理してみましょう。←すごい！！night ticketもあるのでticketPriceにしました(hase)
    /**
     * Buy N-day / M-night passport, method for park guest. (N = 1,2,4) (M = 2)
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @param ticketPrice 各チケットの料金
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    private Ticket buyNDayPassport(Integer handedMoney, Integer ticketPrice, Integer nDays, Boolean onlyNightAvailable) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        // TODO done hase ここ少しコメントの補足欲しいですね。マイナスpriceだったら、プラスに戻してる？のはなぜ？ by jflute (2025/07/02)
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
        TicketBuyResult result = new TicketBuyResult(handedMoney, ticketPrice, nDays, onlyNightAvailable); // チケット購入結果を取得
        Ticket ticket = result.getTicket(); // 購入したチケットを取得
        change = result.getChange(); // お釣りを取得
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + ticketPrice;
        } else { // first purchase
            salesProceeds = ticketPrice;
        }
        --quantity;
        return ticket;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public int getSalesProceeds() {
        return salesProceeds;
    }

    public int getChange() {
        return change;
    }
}
