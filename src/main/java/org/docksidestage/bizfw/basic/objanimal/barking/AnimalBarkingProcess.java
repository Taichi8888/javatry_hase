package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Creature;

/**
 * Animalが鳴くプロセス
 * @author tahasega
 */
public class AnimalBarkingProcess extends  BarkingProcess {
    public AnimalBarkingProcess(Creature creature) {
        super(creature);
    }
    
    // TODO hase [ふぉろー] こういうコメントあるといいかも by jflute (2025/07/16)
    // e.g. いま空っぽだけど、何かanimalで固有処理ができたら追加していくこと
}
