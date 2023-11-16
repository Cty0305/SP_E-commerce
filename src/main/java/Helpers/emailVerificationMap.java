package Helpers;

import java.sql.Timestamp;

public class emailVerificationMap {

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    private String account;
    private Long timestamp;
}
