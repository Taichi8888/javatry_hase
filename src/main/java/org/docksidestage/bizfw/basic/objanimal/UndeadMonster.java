package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.UndeadBarkingProcess;

/**
 * The object for undead monster.
 * @author tahasega
 */
public abstract class UndeadMonster extends Creature {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // TODO hase final付けられるので付けておきましょう by jflute (2025/07/22)
    protected UndeadDiary undeadDiary;
//    protected final UndeadBarkingProcess undeadBarkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public UndeadMonster() {
        // #1on1 さらなる Factory Method の拡張ポイントについて。
        // TODO hase 1on1でさらなる拡張ポイントの話をしたので、IntelliJのショートカットを使ってFactoryしてみましょう by jflute (2025/07/22)
        // shift+shift or shift+command+A でコマンドを探すやり方でもOK。
        // TODO hase [読み物課題] リファクタリングは思考のツール by jflute (2025/07/22)
        // https://jflute.hatenadiary.jp/entry/20121202/1354442627
        // (指が面倒だと思うと、直したほうが良いコードだと思ってもやらなくなる)
        this.undeadDiary = new UndeadDiary();
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    @Override
    protected UndeadBarkingProcess createBarkingProcess() {
        return new UndeadBarkingProcess(this);
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
    public abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    public void downHitPoint() {
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

