package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Creature;
import org.docksidestage.bizfw.basic.objanimal.UndeadMonster;

/**
 * UndeadMonsterが鳴くプロセス
 * @author tahasega
 */
public class UndeadBarkingProcess extends BarkingProcess{

    // TODO hase ここは Undead と言い切ってますから、引数の型も Undead で良いかと by jflute (2025/07/15)
    public UndeadBarkingProcess(Creature creature) {
        super(creature);
    }
    @Override
    protected void breatheIn(Creature creature) {
        logger.debug("...Breathing in for barking");
        // TODO hase [いいね]ダウンキャストに対する念のための処理をしっかり丁寧にしているの素晴らしい by jflute (2025/07/15)
        // TODO hase 修行++: 一方で、このダウンキャストうーむーな感じですよね...ここまで階層化しておいてダウンキャストが発生しちゃうのも... by jflute (2025/07/15)
        // 他のtodoやってたら自然とダウンキャスト要らなくなるとかあるかも!?
        try { // もし何かの間違いでUndeadMonster以外のCreatureが来たとき用に by hase (2025/07/15)
            UndeadMonster undeadMonster = (UndeadMonster) creature;
            undeadMonster.getUndeadDiary().countBreatheIn();
            creature.downHitPoint(); // 一応、UndeadMonsterでもダメージ受けるモンスターがいるかもしれないので残す
        } catch (ClassCastException e) {
            // TODO hase キャストできなかった creature をメッセージに残しておきましょう by jflute (2025/07/15)
            // ネストの e で情報出てくるかもしれないけど、ないこともあるから自分のメッセージにデバッグしやすい情報を載せましょう。
            // TODO hase [読み物課題] 例外メッセージ、敬語で満足でもロスロスパターン by jflute (2025/07/15)
            // https://jflute.hatenadiary.jp/entry/20170804/explossloss
            logger.warn("Creature is not UndeadMonster", e);
        }
    }
}
