package org.docksidestage.bizfw.basic.buyticket;

// #1on1: オブジェクト指向の知識自体は簡単に学べて覚えることはできる。 (2025/08/19)
// けど、実際のオブジェクトの整理整頓スキルは、なんども繰り返して積み上げていかないといけない。
// (オブジェクト指向に限らず、プログラミングデザインに関するものはすべて同じ)
/**
 * チケットの種類（価格、使用可能日数、夜間限定フラグ）を表すクラス
 * @author tahasega
 */
public enum TicketType {
    ONE_DAY(7400, 1, false),
    TWO_DAY(13200, 2, false),
    FOUR_DAY(22400, 4, false),
    TWO_NIGHT(7400, 2, true, 17, 22);
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int ticketPrice; // チケットの価格
    private final int maxDays; // チケットの使用可能日数
    private final boolean onlyNightAvailable; // 夜間限定フラグ
    
    // TODO hase コメントで具体値を書くと、いつかズレる問題があるかもなので、ちょっとぼかす by jflute (2025/08/19)
    // e.g. （デフォルトでは17時）→（初期値は業務上のデフォルト）
    // #1on1: コメントって本体のコードがあること前提の文章なので、コードで載ってることを書く必要はない
    // TODO hase nightのみで使う変数ということで、それをコメントで表現しておきたいところ by jflute (2025/08/19)
    //  e.g.
    //   // nightのチケットのときのみ利用される想定
    //   private int nightStartHour = 17;
    //   ...
    // 一方で、構造的に解決ができたら一番ではあるけど。
    // hint: 22時の方は、nightのチェックというよりかは、終わりの時間のチェックとも言えるので...
    // 質問 by haseさん: enumの定義のところが 8, 22 だらけになって冗長な感じに
    // 回答 by jflute: オーソドックスな時間帯とかノーマルな時間帯という概念で参照すれば
    // hint2: 今やってるデフォルト値のやり方を踏襲して、そこでオーソドックスな時間帯を表現してもいいかも。
    private int nightStartHour = 17; // 夜間の開始時刻（デフォルトでは17時）
    private int nightEndHour = 21; // 夜間の終了時刻（デフォルトでは21時）

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int ticketPrice, int maxDays, boolean onlyNightAvailable) {
        this.ticketPrice = ticketPrice;
        this.maxDays = maxDays;
        this.onlyNightAvailable = onlyNightAvailable;
    }

    TicketType(int ticketPrice, int maxDays, boolean onlyNightAvailable, int nightStartHour, int nightEndHour) {
        this.ticketPrice = ticketPrice;
        this.maxDays = maxDays;
        this.onlyNightAvailable = onlyNightAvailable;
        this.nightStartHour = nightStartHour;
        this.nightEndHour = nightEndHour;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getTicketPrice() {
        return ticketPrice;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public  boolean isOnlyNightAvailable() {
        return onlyNightAvailable;
    }

    public int getNightStartHour() {
        return nightStartHour;
    }

    public int getNightEndHour() {
        return nightEndHour;
    }
}
