package recycleviewc;

public class Listitem {
    private String datemesconstat,Cin1,Cin2,mesRcode,sts;


    public Listitem(String datemesconstat, String Cin1, String Cin2, String mesRcode,String sts) {
        this.datemesconstat = datemesconstat;
        this.Cin1 = Cin1;
        this.Cin2 = Cin2;
        this.sts = sts;
        this.mesRcode = mesRcode;
    }

    public String getDatemesconstat() {
        return datemesconstat;
    }

    public void setDatemesconstat(String datemesconstat) {
        this.datemesconstat = datemesconstat;
    }

    public String getCin1() {
        return Cin1;
    }

    public void setCin1(String cin1) {
        this.Cin1 = cin1;
    }

    public String getCin2() {
        return Cin2;
    }

    public void setCin2(String cin2) {
        this.Cin2 = cin2;
    }

    public String getMesRcode() {
        return mesRcode;
    }
    public String getSts() {
        return sts;
    }

    public void setMesRcode(String mesRcode) {
        this.mesRcode = mesRcode;
    }
}
