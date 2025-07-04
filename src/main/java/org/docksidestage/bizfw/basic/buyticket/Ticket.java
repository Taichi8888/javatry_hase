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

/**
 * チケットの管理（価格、入場、残り入場可能回数、使用済み、夜間限定）
 * @author jflute
 * @author tahasega
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice; // written on ticket, park guest can watch this
    private boolean alreadyIn; // true means this ticket is unavailable
    // TODO done hase フラグはできるだけ、falseからtrueになる、(降りてる旗が上がる) にしたいところですね by jflute (2025/07/02)
    // あと、変数のライフサイクル的には、doInPark()したらfalseに変わっていますので...
    // 「1回目の入園」という状態を示すニュアンスなのかもですが、そうだとすると最初からtrueなのが少し違和感出ます。
    // (ぼくだと、firstTimeDone = false; で、doInPark()が一回呼ばれたら true になるとか、かなぁ...)
//    private boolean firstTimeDone = false; // 2日以上有効なチケットの初日判定
    private int daysLeft; // チケットの残日数管理
    private final boolean onlyNightAvailable; // 夜限定フラグ

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int daysLeft, boolean onlyNightAvailable) {
        this.displayPrice = displayPrice;
        this.onlyNightAvailable = onlyNightAvailable;
        this.daysLeft = daysLeft;
    }


    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) { // すでに入園済みなら、入園できない
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if (onlyNightAvailable) { // 夜間限定チケットは、日中の入園はできない
            throw new IllegalStateException("This ticket is not available for day park: displayedPrice=" + displayPrice);
        }
//        if (!firstTimeDone) {
//            // TODO done hase せめて、TicketBoothに定義してある定数を使いたいですね。 by jflute (2025/07/02)
//            // 値段が変わったときに、あっちもこっちも修正しないと、あっ修正漏れ、になりやすいので。
//            // TODO done hase 修行++: でも、究極は、ここでこういったif-else-if自体をやめたいところですね。 by jflute (2025/07/02)
//            // もし、チケットの種類が増えたとき、TicketBoothだけじゃなくここのelse-ifも増やさないといけなくなります。
//            if (displayPrice == TicketBooth.ONE_DAY_PRICE && !onlyNightAvailable) {
//                daysLeft = 1; // oneDay
//            } else if (displayPrice == TicketBooth.TWO_DAY_PRICE && !onlyNightAvailable) {
//                daysLeft = 2; // twoDays
//            } else if (displayPrice == TicketBooth.FOUR_DAY_PRICE && !onlyNightAvailable) {
//                daysLeft = 4; // fourDays
//            } else if (displayPrice == TicketBooth.TWO_NIGHT_PRICE && onlyNightAvailable) {
//                daysLeft = 2; // twoNights
//            }
//            firstTimeDone = true;
//        }
        daysLeft--; // 入園したので、残り日数を1減らす
        if (daysLeft == 0) { // 残り日数が0になったら、入園済みとする
            alreadyIn = true;
        }
    }
    public void doInNightPark() {
        if (alreadyIn) { // すでに入園済みなら、入園できない
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        // nDayPassportは夜間入場もOKなので、onlyNightAvailableはfalseでもOK
        daysLeft--; // 入園したので、残り日数を1減らす
        if (daysLeft == 0) { // 残り日数が0になったら、入園済みとする
            alreadyIn = true;
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    // TODO done hase こういうのもあるはあるんですが、ただオーソドックスにはisOnly...というようにisを付けます by jflute (2025/07/02)
    // ↑でも isAlreadyIn() と is 方式ですから、合わせた方が良いかなと。
    // is以外だと、has,existsなどの三単現の動詞、can/may/shouldなどの助動詞がbooleanでよく使われます。←勉強になります！！ありがとうございます。
    public boolean isOnlyNightAvailable() {
        return onlyNightAvailable;
    }
}
