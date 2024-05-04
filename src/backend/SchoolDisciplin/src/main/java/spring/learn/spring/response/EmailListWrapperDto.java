package spring.learn.spring.response;

import java.io.Serializable;
import java.util.List;

public class EmailListWrapperDto implements Serializable {
    private List<String> emailList;

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public EmailListWrapperDto() {
    }

    public EmailListWrapperDto(List<String> emailList) {
        this.emailList = emailList;
    }

}
