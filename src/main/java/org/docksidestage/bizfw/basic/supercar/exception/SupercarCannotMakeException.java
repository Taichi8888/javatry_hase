package org.docksidestage.bizfw.basic.supercar.exception;

/**
 * スーパーカーが作れなかった時の例外クラス
 * @author tahasega
 */
public class SupercarCannotMakeException extends RuntimeException {

    public SupercarCannotMakeException(String msg) {
        super(msg);
    }

    public SupercarCannotMakeException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
