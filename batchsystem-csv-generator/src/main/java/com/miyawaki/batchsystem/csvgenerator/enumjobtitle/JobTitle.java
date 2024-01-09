package com.miyawaki.batchsystem.csvgenerator.enumjobtitle;

public enum JobTitle {
    AD_PRES("President"),
    AD_VP("Administration Vice President"),
    AD_ASST("Administration Assistant"),
    FI_MGR("Finance Manager"),
    FI_ACCOUNT("Accountant"),
    AC_MGR("Accounting Manager"),
    AC_ACCOUNT("Public Accountant"),
    SA_MAN("Sales Manager"),
    SA_REP("Sales Representative"),
    PU_MAN("Purchasing Manager"),
    PU_CLERK("Purchasing Clerk"),
    ST_MAN("Stock Manager"),
    ST_CLERK("Stock Clerk"),
    SH_CLERK("Shipping Clerk"),
    IT_PROG("Programmer"),
    MK_MAN("Marketing Manager"),
    MK_REP("Marketing Representative"),
    HR_REP("Human Resources Representative"),
    PR_REP("Public Relations Representative");

    private final String title;

    JobTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getJobId() {
        return this.name(); // Enumの名前をJOB_IDとして返す
    }
}