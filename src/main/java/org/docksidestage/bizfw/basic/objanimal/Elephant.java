package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.vehicle.Ridable;

/**
 * 象のオブジェクト
 * @author tahasega
 */
public class Elephant extends Animal implements Ridable {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done hase 初期値の = false は省略されることが多いので、bien; でOKです。 by jflute (2025/07/07)
    protected boolean bien; // フランス語のbienじゃなくて「鼻炎」

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Elephant() {
    }

    @Override
    protected int getInitialHitPoint() {
        return 20; // 象は大きいのでHPも大きい
    }

    // ===================================================================================
    //                                                                                Bark
    //                                                                            ========
    @Override
    protected String getBarkWord() {
        return "paon"; // what in English
    }

    // ===================================================================================
    //                                                                            HitPoint
    //                                                                            ========
    @Override
    protected void downHitPoint() {
        super.downHitPoint();
        if (isBien()) {
            super.downHitPoint();
            super.downHitPoint(); // 鼻炎でダメージ3倍！！
        }
    }

    // ===================================================================================
    //                                                                               Fight
    //                                                                            ========
    // done hase このOverrideのコメントアウトはなんでしょうか？ by jflute (2025/07/07)
    // コメントアウトは便利な道具ですが、本当に不要なら消して、残すなら残してる理由のコメントを添えましょう。
    // コメントアウトに関するつぶやき:
    // https://x.com/jflute/status/1421043341286772739
    // fight()をAnimalに持たせるか、Elephantだけでいいか迷った記憶があります...
    // その名残の謎コメントアウトだと思います、すみません by hase (2025/07/08)
    @Override
    public void fight(Creature creature) {
        super.fight(creature);
        this.bien = true;
    }

    // ===================================================================================
    //                                                                                Ride
    //                                                                            ========
    public void ride() {
        this.bien = false; // medicine given ...
        downHitPoint();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isBien() {
        return bien;
    }
}
