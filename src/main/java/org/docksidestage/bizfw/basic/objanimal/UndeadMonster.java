package org.docksidestage.bizfw.basic.objanimal;

/**
 * The object for undead monster.
 * @author tahasega
 */
public abstract class UndeadMonster extends Creature {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final UndeadDiary undeadDiary;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public UndeadMonster() {
        this.undeadDiary = new UndeadDiary();
    }

    @Override // creatureクラス作成時に復活
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
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

