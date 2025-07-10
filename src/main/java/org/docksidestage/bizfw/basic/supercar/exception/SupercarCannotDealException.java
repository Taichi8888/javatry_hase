package org.docksidestage.bizfw.basic.supercar.exception;

/**
 * スーパーカーが取引できなかった時の例外クラス
 * @author tahasega
 */
public class SupercarCannotDealException extends RuntimeException {

    public SupercarCannotDealException(String message) {
        super(message);
    }
}
