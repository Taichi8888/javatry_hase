package org.docksidestage.bizfw.basic.objanimal;

import java.util.function.IntConsumer;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

// #1on1: 久しぶりだとコーディング感覚が下がってしまう by haseさん
// 知ってても間を開けるとできてたことができなくなるのはスポーツもプログラミングも同じ。
// done hase [読み物課題]「ミング」の時間ですよ by jflute (2025/08/19)
// https://jflute.hatenadiary.jp/entry/20121016/ming
/**
 * The object for creature.
 * @author tahasega
 */
public abstract class Creature implements Loudable {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP
    // done hase おもいでにしちゃったけど、FactoryMethodパターンというのをちょっと調べてみてください by jflute (2025/07/15)
    // せっかく BarkingProcess も Creature レベルで抽象化できているのに、
    // Creature のところに BarkingProcess を登場させずに Animal と Undead にそれぞれ定義してるってのはもったいないです。
    // オブジェクト指向にはオーバーライドという技があるので、newするところだけ差し替ガガガガ

    protected final BarkingProcess barkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Creature() {
        hitPoint = getInitialHitPoint();
        barkingProcess = createBarkingProcess();
    }

    protected abstract int getInitialHitPoint();

    // done hase [いいね] おおぉ、完璧な「FactoryMethod によるポリモーフィズム」 by jflute (2025/07/16)
    protected abstract BarkingProcess createBarkingProcess();
    // factory method!!! by hase (2025/07/16)
    // newの代わりに、生成処理をサブクラスに任せて柔軟に。
    // 共変戻り値型：オーバーライド時に戻り値型を親クラスの戻り値型のサブタイプにできる。
    // （例：BarkingProcess → AnimalBarkingProcess）

    // ===================================================================================
    //                                                                                Bark
    //                                                                              ======
    public BarkedSound bark() {
        return this.barkingProcess.bark();
    }

    // TODO hase ここも、publicじゃなくてprotectedをキープしたい by jflute (2025/09/16)
    // こっちは更新系じゃないから、そんな実務的に困るものではないが...
    // やはり元々protectedだったものなので、BarkingProcess切り出しというリファクタリングきっかけでpublicにしたくはない。
    // こっちはもっと解決は簡単ですね。
    public abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // done hase 修行++: public を protected に戻したいところですね by jflute (2025/07/16)
    // BarkingProcess や package移動のリファクタリングの影響でスコープ的に呼べなくなって、 
    // publicにして回避してしまっていますが、元々隠れてたってことは隠したいものではあるので。
    // これはまた悩むかと思いますが、ちょっと考えてみてください。

    // 流石に継承して使うわけにはいかないので、新しい窓口を作ることにしました。by hase (2025/07/18)
    // ただdownHitPoint()だけを書くのも寂しいので、引数も取れるようにしました、そんなに深い意味はないです。
    // done hase [いいね] まあ代替となる、かつ、publicでも良いような業務を一つ作って回避した by jflute (2025/07/22)
    // done hase 修行#: step8の技術を使って、getTired()なしでprotectedキープをしてみましょう by jflute (2025/07/22)
    // hint1: step8の前半
    // MEMO hase コールバックでできそうな情報があったよ、来週頑張れ by hase (2025/08/01)
    protected abstract void downHitPoint();

//    // done hase [いいね] "ゲッターじゃないよ" がとても興味ふかい(^^ by jflute (2025/07/22)
//    public void getTired() { // ゲッターじゃないよ
//        downHitPoint();
//    }
//
//    public void getTired(int damage) { // ゲッターじゃないよ
//        if (damage > 0) {
//            for (int i = 0; i < damage; i++) {
//                downHitPoint();
//            } // HPが0以下になった時の処理はdownHitPoint()に任せてあるのでここでは記述しない
//        }
//    }

    // 自信がないです。by hase (2025/08/19)
    // コールバックを使うことで拡張性は増したと思いますが、結局は以前のgetTired()と同じような安全性・保守性になっている気がします。
    // done hase hint: コールバックの方向が逆ですね... by jflute (2025/08/19)
    // #1on1: コールバックでdownHitPoint()が呼べたらいいのに...って発言をご自身でしている。
    // (コールバックの方向: A から B に電話を掛けて... Bが後から A に電話を掛け直す)
    // done hase hint2: 自分でちゃんとした方向のコールバック、TicketTypeのsupplierでできてるけど by jflute (2025/09/02)
    // (#1on1: void set...のメソッドのスタイルが似てるではなく、あくまでコールバックの方向が)
// おもいで：コールバック苦戦中 by hase (2025/9/16)
//    public void getTiredWithCallback(int damage, IntConsumer callback) { // ゲッターじゃないよ
//        if (damage > 0) {
//            for (int i = 0; i < damage; i++) {
//                downHitPoint();
//                callback.accept(i + 1);
//            } // HPが0以下になった時の処理はdownHitPoint()に任せてあるのでここでは記述しない
//        }
//    }
    // コールバックの方向はこれでいい気がするけど、結局publicで呼び出しちゃってるから意味があるのかしら... by hase (2025/09/16)
    private final IntConsumer downHitPointCallback = (damage) -> {
        for (int i = 0; i < damage; i++) {
            downHitPoint();
        }
    };

    // TODO hase 修行#++: downHitPoint自体はインターフェースで抽象化して旅立てるようになっているのでGood by jflute (2025/09/16)
    // でも、downHitPointCallbackの利用者が、結局Creatureのpublicメソッドになってしまっている。
    // hint3: コールバックの逆の話は解消した!?と言えるかも。ただ、コールバックの利用者はここじゃないみたいな...
    //
    // downHitPointCallback変数をpublicにしちゃダメだから、ここでしか呼べないのでは？ by はせ
    // downHitPointCallback変数自体へのアクセスは確かにprivateじゃないといけない by jflute
    // hint4: ただ、downHitPointCallbackが指し示すコールバックインスタンスは、ピンポイントで旅立たせることはできる。
    //
    // hint5: step1での話、test_のseaと、helpのseaで同じインスタンスを共有している。
    //  o test_variable_method_argument_mutable_methodcall()
    //  o helpMethodArgumentMethodcall()
    // 二つメソッドだけで共有していると言える。public的なものでもないのに、ピンポイントで共有している。
    // 「わたしてしまえばいいのか」 by はせ
    public void getTired(int damage) { // ゲッターじゃないよ(再)
        downHitPointCallback.accept(damage);
    }

    // ===================================================================================
    //                                                                               Fight
    //                                                                              ======
    public abstract void fight(Creature creature);

    // ===================================================================================
    //                                                                                Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                           =========
    public int getHitPoint() {
        return hitPoint;
    }
}
