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

import java.time.LocalDateTime;

/**
 * チケットの管理（価格、入場、残り入場可能回数、使用済み、夜間限定）
 * @author jflute
 * @author tahasega
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done hase alreadyInだけ、Contructorで受け取らない純粋な状態を表すmutable変数なので、宣言の並びも一番下とかに移動しましょう by jflute (2025/07/07)
    // done hase フラグはできるだけ、falseからtrueになる、(降りてる旗が上がる) にしたいところですね by jflute (2025/07/02)
    // あと、変数のライフサイクル的には、doInPark()したらfalseに変わっていますので...
    // 「1回目の入園」という状態を示すニュアンスなのかもですが、そうだとすると最初からtrueなのが少し違和感出ます。
    // (ぼくだと、firstTimeDone = false; で、doInPark()が一回呼ばれたら true になるとか、かなぁ...)
    // private boolean firstTimeDone = false; // 2日以上有効なチケットの初日判定
// おもいで：price, days, onlyNightをticketで管理していた時代
//    private final int ticketPrice; // written on ticket, park guest can watch this
//    private final boolean onlyNightAvailable; // 夜限定フラグ
    private final TicketType ticketType;
    private int daysLeft; // チケットの残日数管理
    private boolean alreadyIn; // true means this ticket is unavailable

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(TicketType ticketType) {
        // done hase 代入行の順番、できればConstructorの引数の順番に合わせましょう by jflute (2025/07/07)
        // そういったところ整ってるだけで、読み手はノイズなく直感的に読みやすくなるので。
        // ちなみに、インスタンス変数の定義順序も同じです。
        // 何か理由があれば順序通りじゃなくてもいいですが、特になければこの辺から一通り合わせてくれた方がありがたいです。
// おもいで：price, days, onlyNightをticketで管理していた時代
//        this.ticketPrice = ticketType.getTicketPrice();
//        this.onlyNightAvailable = ticketType.isOnlyNightAvailable(); // 夜間限定フラグ
        this.ticketType = ticketType;
        this.daysLeft = ticketType.getMaxDays(); // チケットの使用可能日数
    }


    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) { // すでに入園済みなら、入園できない
            throw new IllegalStateException("Already in park by this ticket.");
        }
        // 夜間入場処理もdoInPark()で行うように変更
        if (isOnlyNightAvailable() && !isNightTime()) {
            throw new IllegalStateException("This ticket is not available for day park.");
        }
// おもいで
//        if (!firstTimeDone) {
//            // done hase せめて、TicketBoothに定義してある定数を使いたいですね。 by jflute (2025/07/02)
//            // 値段が変わったときに、あっちもこっちも修正しないと、あっ修正漏れ、になりやすいので。
//            // done hase 修行++: でも、究極は、ここでこういったif-else-if自体をやめたいところですね。 by jflute (2025/07/02)
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
        // done hase すごく良いコメントなのですが、ちょこっとだけ値をオウム返ししているので、少しだけ抽象化しましょう by jflute (2025/07/07)
        // e.g.
        //  daysLeft--; // 入園したので、残り日数を減らす
        //  if (daysLeft == 0) { // 入園済みとする
        //
        // done hase [読み物課題] オートマティックおうむ返しコメントより背景や理由を by jflute (2025/07/07)
        // https://jflute.hatenadiary.jp/entry/20180625/repeatablecomment
        // (ブログを読むのもjavatryということでじっくり読んでもらえればと)
        //
        daysLeft--; // 入園したので、残り日数を減らす
        if (daysLeft == 0) { // 入園済みとする
            alreadyIn = true;
        }
    }

    // done hase [いいね] isメソッドに切り出してるのGoodですねー。わかりやすい by jflute (2025/07/07)
    // done hase 内部だけで使う想定のメソッドなら、publicではなくprivateにしましょう by jflute (2025/07/07)
    // done hase そして、AccessorというよりかはLogicなので、doInPark()の直下あたりに宣言するで良いと思います by jflute (2025/07/07)
    private boolean isNightTime() { // 夜間判定
        return LocalDateTime.now().getHour() >= 17 && LocalDateTime.now().getHour() <= 21;
    }
    // done hase 修行++: なるほど、呼び出し側がNightかどうかを呼び分けるって形にしたんですね。それはそれで一つの解決策ですね by jflute (2025/07/07)
    // ただ、TicketでonlyNightAvailableの状態まで持っていますので、できれば一つのdoInPark()で、
    // 現在が昼だったら入れる/入れない、夜だったら入れる/入れないという風に制御できると呼び出し側がもうちょい楽になるかなと。
    // (いまの実装だと、結局昼の時間帯でも doInNightPark() が呼び出されちゃったら動きますもんね)
//    public void doInNightPark() {
//        if (alreadyIn) { // すでに入園済みなら、入園できない
//            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
//        }
//        // nDayPassportは夜間入場もOKなので、onlyNightAvailableはfalseでもOK
//        daysLeft--; // 入園したので、残り日数を1減らす
//        if (daysLeft == 0) { // 残り日数が0になったら、入園済みとする
//            alreadyIn = true;
//        }
//    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // 先にTicketクラスのインスタンス変数のaccessor by hase (2025/07/08)
    public TicketType getTicketType() {
        return ticketType;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    // 次にTicketTypeクラスのインスタンス変数のaccessor by hase (2025/07/08)
    // done jflute (質問です) ticketPrice, maxDays, onlyNightAvailableは、TicketTypeクラスの責務であると定義したのですが、
    //  Ticketクラスからも参照する方が直感的であると思ったため、両クラスから取得できる形で書きました。
    //  責務は一つに集約すべきでしょうか？ by hase (2025/07/08)
    // TODO hase [へんじ] おお、これは悩ましいですね。スッキリさを優先するのであればTicketTypeさえgetできれば... by jflute (2025/07/09)
    // いいわけですけど、Ticket自身が提供できていい情報なんじゃないか (直感的) ってのも一理あります。
    // ぼく自身、両方のパターンがあったなぁと。ケースバイケースだと思うのですが、その線引きが確かに言語化できないですね。
    // なので、直感的と思ったhaseさんの感覚で良いと思います。
    // 人から「要らないのでは？」って言われたとき、「その方が直感的だと思いました」と堂々と言えれば良いです。
    // 正解はないので、あとは決めの問題になるかなと。
    public int getTicketPrice() {
        return ticketType.getTicketPrice();
    }

    public int getMaxDays() {
        return ticketType.getMaxDays();
    }

    public boolean isOnlyNightAvailable() {
        return ticketType.isOnlyNightAvailable();
    }
    // done hase こういうのもあるはあるんですが、ただオーソドックスにはisOnly...というようにisを付けます by jflute (2025/07/02)
    // ↑でも isAlreadyIn() と is 方式ですから、合わせた方が良いかなと。
    // is以外だと、has,existsなどの三単現の動詞、can/may/shouldなどの助動詞がbooleanでよく使われます。←勉強になります！！ありがとうございます。
}
