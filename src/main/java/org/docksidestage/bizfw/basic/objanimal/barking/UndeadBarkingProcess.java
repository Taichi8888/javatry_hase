package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Creature;
import org.docksidestage.bizfw.basic.objanimal.UndeadMonster;

/**
 * UndeadMonsterが鳴くプロセス
 * @author tahasega
 */
public class UndeadBarkingProcess extends BarkingProcess{

    public UndeadBarkingProcess(Creature creature) {
        super(creature);
    }
    @Override
    protected void breatheIn(Creature creature) {
        logger.debug("...Breathing in for barking");
        try { // もし何かの間違いでUndeadMonster以外のCreatureが来たとき用に by hase (2025/07/15)
            UndeadMonster undeadMonster = (UndeadMonster) creature;
            undeadMonster.getUndeadDiary().countBreatheIn();
            creature.downHitPoint(); // 一応、UndeadMonsterでもダメージ受けるモンスターがいるかもしれないので残す
        } catch (ClassCastException e) {
            logger.warn("Creature is not UndeadMonster", e);
        }
    }
}
