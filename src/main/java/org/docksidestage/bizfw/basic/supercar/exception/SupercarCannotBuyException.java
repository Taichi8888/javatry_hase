package org.docksidestage.bizfw.basic.supercar.exception;

/**
 * スーパーカーが買えなかった時の例外クラス
 * @author tahasega
 */
public class SupercarCannotBuyException extends RuntimeException {

    public SupercarCannotBuyException(String msg) {
        super(msg);
    }

    public SupercarCannotBuyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
