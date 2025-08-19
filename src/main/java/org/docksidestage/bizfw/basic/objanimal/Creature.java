package org.docksidestage.bizfw.basic.objanimal;

import java.util.function.IntConsumer;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

// #1on1: 久しぶりだとコーディング感覚が下がってしまう by haseさん
// 知ってても間を開けるとできてたことができなくなるのはスポーツもプログラミングも同じ。
// TODO hase [読み物課題]「ミング」の時間ですよ by jflute (2025/08/19)
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
    // TODO hase 修行#: step8の技術を使って、getTired()なしでprotectedキープをしてみましょう by jflute (2025/07/22)
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
    // TODO hase hint: コールバックの方向が逆ですね... by jflute (2025/08/19)
    // #1on1: コールバックでdownHitPoint()が呼べたらいいのに...って発言をご自身でしている。
    // (コールバックの方向: A から B に電話を掛けて... Bが後から A に電話を掛け直す)
    public void getTiredWithCallback(int damage, IntConsumer callback) { // ゲッターじゃないよ
        if (damage > 0) {
            for (int i = 0; i < damage; i++) {
                downHitPoint();
                callback.accept(i + 1);
            } // HPが0以下になった時の処理はdownHitPoint()に任せてあるのでここでは記述しない
        }
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
