package com.invoicexpress.postrating.Enum;

public enum VoteEnum {

    UP_VOTE("UpVote"),
    DOWN_VOTE ("DownVote");


    private String shdes;

    VoteEnum(String shdes){
        this.shdes = shdes;
    }
    public String getShdes() {
        return this.shdes;
    }
}
