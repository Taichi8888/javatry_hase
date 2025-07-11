package org.docksidestage.bizfw.basic.supercar.exception;

/**
 * スーパーカーが取引できなかった時の例外クラス
 * @author tahasega
 */
public class SupercarCannotDealException extends RuntimeException {

    public SupercarCannotDealException(String msg) {
        super(msg);
    }

    public SupercarCannotDealException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
