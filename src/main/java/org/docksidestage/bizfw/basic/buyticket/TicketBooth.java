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

// TODO done hase 細かいですが、@authorは2行目を追加しちゃってOKです by jflute (2025/07/02)
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
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAYS_PRICE = 13200;
    private static final int FOUR_DAYS_PRICE = 22400;
    private static final int TWO_DAYS_NIGHT_PRICE = -7400; // 夜間は負の数で管理する

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

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
    // TODO done hase [いいね] NDay でうまく再利用できましたね！ by jflute (2025/07/02)
    // TODO done hase JavaDocが、one-dayのままです by jflute (2025/07/02)
    // チーム開発で非常に大事ですね、気をつけます。
    /**
     * Buy N-day / M-night passport, method for park guest. (N = 1,2,4) (M = 2)
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    private Ticket buyNDayPassport(Integer handedMoney, Integer NDayPrice) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        int priceForCalc = NDayPrice;
        if (priceForCalc < 0) {
            priceForCalc = priceForCalc * -1;
        }
        if (handedMoney < priceForCalc) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        // TODO done hase --quantity; は、if-else の外に出しても良いのでは？ by jflute (2025/07/02)
        // 本当だ...ありがとうございます
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + priceForCalc;
        } else { // first purchase
            salesProceeds = priceForCalc;
        }
        --quantity;
        Ticket ticket = new Ticket(NDayPrice);
        return ticket;
    }

    public Ticket buyOneDayPassport(Integer handedMoney) {
//        if (quantity <= 0) {
//            throw new TicketSoldOutException("Sold out");
//        }
//        if (handedMoney < ONE_DAY_PRICE) {
//            throw new TicketShortMoneyException("Short money: " + handedMoney);
//        }
//        if (salesProceeds != null) { // second or more purchase
//            salesProceeds = salesProceeds + ONE_DAY_PRICE;
//            --quantity;
//        } else { // first purchase
//            salesProceeds = ONE_DAY_PRICE;
//            --quantity;
//        }
        return buyNDayPassport(handedMoney, ONE_DAY_PRICE);
    }

    public Ticket buyTwoDayPassport(Integer handedMoney) {
//        if (quantity <= 0) {
//            throw new TicketSoldOutException("Sold out");
//        }
//        if (handedMoney < TWO_DAYS_PRICE) {
//            throw new TicketShortMoneyException("Short money: " + handedMoney);
//        }
//        if (salesProceeds != null) { // second or more purchase
//            salesProceeds = salesProceeds + TWO_DAYS_PRICE;
//            --quantity;
//        } else { // first purchase
//            salesProceeds = TWO_DAYS_PRICE;
//            --quantity;
//        }
//        return handedMoney - TWO_DAYS_PRICE;
//        TicketBuyResult result = new TicketBuyResult(handedMoney, TWO_DAYS_PRICE);
//        return result;
        return buyNDayPassport(handedMoney, TWO_DAYS_PRICE);
    }

    public Ticket buyFourDayPassport(Integer handedMoney) {
        return buyNDayPassport(handedMoney, FOUR_DAYS_PRICE);
    }

    public Ticket buyTwoNightPassport(Integer handedMoney) {
        return buyNDayPassport(handedMoney, TWO_DAYS_NIGHT_PRICE);
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
