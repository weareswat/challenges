package com.invoicexpress.postrating.Enum;

public enum PostEnum {
    SEARCH_SUCCESSFULLY_CONDUCTED           ("Search done successfully"),
    INVALID_POST_ID                         ("Invalid Post Id"),
    UPDATED_POST_VOTE                       ("Update Post Vote");

    private String shdes;

    PostEnum(String shdes){
        this.shdes = shdes;
    }
    public String getShdes() {
        return this.shdes;
    }
}
