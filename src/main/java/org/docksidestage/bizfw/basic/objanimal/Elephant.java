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
    // TODO done hase 初期値の = false は省略されることが多いので、bien; でOKです。 by jflute (2025/07/07)
    protected boolean bien;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Elephant() {
    }

    // ===================================================================================
    //                                                                                Bark
    //                                                                            ========
    public String getBarkWord() {
        return "paon"; // what in English
    }

    // ===================================================================================
    //                                                                               Fight
    //                                                                            ========
    // TODO done hase このOverrideのコメントアウトはなんでしょうか？ by jflute (2025/07/07)
    // コメントアウトは便利な道具ですが、本当に不要なら消して、残すなら残してる理由のコメントを添えましょう。
    // コメントアウトに関するつぶやき:
    // https://x.com/jflute/status/1421043341286772739
    // fight()をAnimalに持たせるか、Elephantだけでいいか迷った記憶があります...
    // その名残の謎コメントアウトだと思います、すみません by hase (2025/07/08)
    @Override
    public void fight() {
        downHitPoint();
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
    //                                                                            HitPoint
    //                                                                            ========
    @Override
    public void downHitPoint() {
        super.downHitPoint();
        if (isBien()) {
            super.downHitPoint();
            super.downHitPoint();
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isBien() {
        return bien;
    }
}
