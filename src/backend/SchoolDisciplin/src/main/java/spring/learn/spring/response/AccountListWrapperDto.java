package spring.learn.spring.response;

import java.io.Serializable;
import java.util.List;

public class AccountListWrapperDto implements Serializable {
    private List<String> accountList;

    public List<String> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<String> accountList) {
        this.accountList = accountList;
    }

    public AccountListWrapperDto() {
    }

    public AccountListWrapperDto(List<String> accountList) {
        this.accountList = accountList;
    }

}
