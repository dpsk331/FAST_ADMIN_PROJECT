package com.example.study.model;

// 스프링부트에서 jackson 라이브러리를 통해 JSON 형태인 {"account" : "","email" : "","page" :0} 와 같이 변경하려 반환!

public class SearchParam {

    private String account;
    private String email;
    private int page;

   public SearchParam() {}

   public SearchParam(String account) {
       this.account = account;
   }

   public SearchParam(String account, String email, int page) {
       this.account = account;
       this.email = email;
       this.page = page;
   }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

}
