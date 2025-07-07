package org.docksidestage.javatry.basic.st6.os;

/**
 * Macオブジェクト
 * @author tahasega
 */
public class Mac extends St6OperationSystem{
    public Mac(String loginId) {
        super(loginId);
    }

    @Override
    public String getFileSeparator() {
        return "/";
    }

    @Override
    public String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
