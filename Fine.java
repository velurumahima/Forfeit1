package com.ocr.traffic;

public class Fine {

    String vno;

    String date;

    double  amount;

    String reason;

    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getVno()
    {
        return vno;
    }

    public void setVno(String vno)
    {
        this.vno = vno;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
