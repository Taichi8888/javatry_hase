package org.docksidestage.bizfw.basic.objanimal;

import java.util.function.IntConsumer;
import java.util.function.Supplier;

import org.docksidestage.bizfw.basic.objanimal.barking.UndeadBarkingProcess;

/**
 * The object for undead monster.
 * @author tahasega
 */
public abstract class UndeadMonster extends Creature {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done hase final付けられるので付けておきましょう by jflute (2025/07/22)
    protected final UndeadDiary undeadDiary;
//    protected final UndeadBarkingProcess undeadBarkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public UndeadMonster() {
        // #1on1 さらなる Factory Method の拡張ポイントについて。
        // done hase 1on1でさらなる拡張ポイントの話をしたので、IntelliJのショートカットを使ってFactoryしてみましょう by jflute (2025/07/22)
        // shift+shift or shift+command+A でコマンドを探すやり方でもOK。
        // done hase [読み物課題] リファクタリングは思考のツール by jflute (2025/07/22)
        // https://jflute.hatenadiary.jp/entry/20121202/1354442627
        // (指が面倒だと思うと、直したほうが良いコードだと思ってもやらなくなる)
        // #1on1: 問題にぶち当たったときこそ、整理整頓(リファクタリング)で全体像把握、論理を整理s
        this.undeadDiary = createUndeadDiary();
    }
    private static UndeadDiary createUndeadDiary() {
        return new UndeadDiary();
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    @Override
    protected UndeadBarkingProcess createBarkingProcess(IntConsumer downHitPointCallback, Supplier<String> getBarkWordCallback) {
        return new UndeadBarkingProcess(this, downHitPointCallback, getBarkWordCallback);
    }

    public static class UndeadDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                               Fight
    //                                                                              ======
    @Override
    public void fight(Creature creature) {
        creature.downHitPoint();
        creature.downHitPoint(); // アンデッドは2回攻撃
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public UndeadDiary getUndeadDiary() {
        return undeadDiary;
    }
}

