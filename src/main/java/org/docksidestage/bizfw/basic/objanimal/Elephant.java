package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.vehicle.Ridable;

/**
 * 象のオブジェクト
 * @author tahasega
 */
public class Elephant extends Animal implements Ridable {

    // TODO hase 初期値の = false は省略されることが多いので、bien; でOKです。 by jflute (2025/07/07)
    protected boolean bien = false;

    public boolean isBien() {
        return bien;
    }

    public Elephant() {
    }

    public String getBarkWord() {
        return "paon"; // what in English
    }
    // TODO hase このOverrideのコメントアウトはなんでしょうか？ by jflute (2025/07/07)
    // コメントアウトは便利な道具ですが、本当に不要なら消して、残すなら残してる理由のコメントを添えましょう。
    // コメントアウトに関するつぶやき:
    // https://x.com/jflute/status/1421043341286772739
//    @Override
    public void fight() {
        downHitPoint();
        this.bien = true;
    }

    @Override
    public void downHitPoint() {
        super.downHitPoint();
        if (isBien()) {
            super.downHitPoint();
            super.downHitPoint();
        }
    }

    public void ride() {
        this.bien = false; // medicine given ...
        downHitPoint();
    }
}
