package org.docksidestage.bizfw.basic.buyticket;

// #1on1: オブジェクト指向の知識自体は簡単に学べて覚えることはできる。 (2025/08/19)
// けど、実際のオブジェクトの整理整頓スキルは、なんども繰り返して積み上げていかないといけない。
// (オブジェクト指向に限らず、プログラミングデザインに関するものはすべて同じ)

// #1on1: お部屋の片付けがプログラミングにつながると考えれば効率良い。 (2025/09/02)
// し、プログラミング頑張ればお部屋の片付けが上手になる(かも!?)と考えれば効率良い。

import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * チケットの種類（価格、使用可能日数、夜間限定フラグ）を表すクラス
 * @author tahasega
 */
public enum TicketType {
    ONE_DAY(7400, 1, false),
    TWO_DAY(13200, 2, false),
    FOUR_DAY(22400, 4, false),
    TWO_NIGHT(7400, 2, true, 17, 21);
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int ticketPrice; // チケットの価格
    private final int maxDays; // チケットの使用可能日数
    private final boolean onlyNightAvailable; // 夜間限定フラグ
    
    // done hase コメントで具体値を書くと、いつかズレる問題があるかもなので、ちょっとぼかす by jflute (2025/08/19)
    // e.g. （デフォルトでは17時）→（初期値は業務上のデフォルト）
    // #1on1: コメントって本体のコードがあること前提の文章なので、コードで載ってることを書く必要はない
    // done hase nightのみで使う変数ということで、それをコメントで表現しておきたいところ by jflute (2025/08/19)
    //  e.g.
    //   // nightのチケットのときのみ利用される想定
    //   private int nightStartHour = 17;
    //   ...
    // 一方で、構造的に解決ができたら一番ではあるけど。
    // hint: 22時の方は、nightのチェックというよりかは、終わりの時間のチェックとも言えるので...
    // 質問 by haseさん: enumの定義のところが 8, 22 だらけになって冗長な感じに
    // 回答 by jflute: オーソドックスな時間帯とかノーマルな時間帯という概念で参照すれば
    // hint2: 今やってるデフォルト値のやり方を踏襲して、そこでオーソドックスな時間帯を表現してもいいかも。
    // hint2の方法で実装しました！by hase (2025/08/19)
    // TODO done hase 終了時刻の含む含まないニュアンス、この変数のところに一言あった方が良い by jflute (2025/09/02)
    // (つまり、データとしての定義を説明)
    private int startHour = 8; // チケット使用可能開始時刻。{startHour}時から入園可能
    private int endHour = 20; // チケット使用可能終了時刻。{endHour}時台までは入園可能

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int ticketPrice, int maxDays, boolean onlyNightAvailable) {
        this.ticketPrice = ticketPrice;
        this.maxDays = maxDays;
        this.onlyNightAvailable = onlyNightAvailable;
    }

    TicketType(int ticketPrice, int maxDays, boolean onlyNightAvailable, int startHour, int endHour) {
        this.ticketPrice = ticketPrice;
        this.maxDays = maxDays;
        this.onlyNightAvailable = onlyNightAvailable;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    // ===================================================================================
    //                                                                       Determination
    //                                                                       =============
    // #1on1: 意味的にはTicketTypeに合ってもしっくり来るものではある (2025/09/02)
    // 一方で、実務だと、enumのmutableを避けるために、...Logicクラスとかに出しちゃう!?
    // 例えば、TicketAvailableTimeDeterminer みたいな専用クラスにしちゃうかも。
    // でも、意味的にはすごく悪くない。
    public boolean isEntryAvailableTime() {
        int hour = currentHourSupplier.get();
        return hour >= startHour && hour <= endHour; //{endHour}時台までは入園可能
    }

// おもいで：テスト時に時間を指定できるように拡張したby hase (2025/08/20)
//    private int getCurrentHour() {
//        return LocalDateTime.now().getHour();
//    }

    // テスト用
    private Supplier<Integer> currentHourSupplier = () -> LocalDateTime.now().getHour();

    // #1on1: IntelliJ, protectedで警告が出る理由, enum はfinal classだから意味ないよ警告 (2025/09/02)
    // #1on1: packageスコープで隠しているので、enumをmutableにしても問題ないと言える。
    // 一方で、enumがmutableであることに少々びっくりする人いるかもしれない...くらい。
    // でも、意味的には isEntryAvailableTime() はここにあっても良いので悪くない。
    void setCurrentHourSupplier(Supplier<Integer> supplier) {
        this.currentHourSupplier = supplier;
    }
    
    // #1on1: Javaのpackageスコープのジレンマ話
    // ファイルの物理構造に依存してしまうのでリファクタリングがやりづらくなる。
    // jflute個人的には、mainの中ではpackageスコープに依存した実装はしないようにしている。
    // 他の言語では、packageとディレクトリ構造が分離されている場合があるので、そのときは問題にならない。
    // ただ、jflute個人的には、物理に直結してるpackageはわりと好き。
    // なぜかというと、やはり直感的にわかるし、ぐちゃぐちゃにならない。
    // (経験上、自由な言語(C#)で、ぐちゃぐちゃでめっちゃクラス探しづらい現場もあった)
    // (サブパッケージスコープがあったら全く文句なかったのに...)
    
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

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
}
