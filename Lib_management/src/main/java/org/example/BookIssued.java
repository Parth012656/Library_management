package org.example;

public class BookIssued {
    private int Bookid;
    private int Issueid;
    private String Iname;
    private String Bname;
    private int Issue;//Number of Book issued
    private String Mobile;
    private String Idate;
    private String Rdate;

    BookIssued(){}

    public BookIssued(int bookid, String iname, String bname, int issue, String mobile) {
        Bookid = bookid;
        Iname = iname;
        Bname = bname;
        Issue = issue;
        Mobile = mobile;
    }

    public BookIssued( int issueid,int bookid, String iname, String bname, int issue, String mobile, String idate, String rdate) {
        Bookid = bookid;
        Issueid = issueid;
        Iname = iname;
        Bname = bname;
        Issue = issue;
        Mobile = mobile;
        Idate = idate;
        Rdate = rdate;
    }

    public int getBookid() {
        return Bookid;
    }

    public void setBookid(int bookid) {
        Bookid = bookid;
    }

    public int getIssueid() {
        return Issueid;
    }

    public void setIssueid(int issueid) {
        Issueid = issueid;
    }

    public String getIname() {
        return Iname;
    }

    public void setIname(String iname) {
        Iname = iname;
    }

    public String getBname() {
        return Bname;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public int getIssue() {
        return Issue;
    }

    public void setIssue(int issue) {
        Issue = issue;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getIdate() {
        return Idate;
    }

    public void setIdate(String idate) {
        Idate = idate;
    }

    public String getRdate() {
        return Rdate;
    }

    public void setRdate(String rdate) {
        Rdate = rdate;
    }

    @Override
    public String toString() {
        // Formatting the output using String.format for consistent width for each column
        return String.format("{ Issueid=%-5d | Bookid=%-5d | Student=%-20s | Bname=%-30s | BookIssued=%-2d | Mobile=%s | Issuedate=%s | Returndate=%s }",
                Issueid, Bookid,  Bname,Iname, Issue, Mobile, Idate, Rdate);
    }
}
