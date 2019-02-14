package com.properties.props;

public class ApplicationProperties {
    private String myprop1;
    private String myprop2;

    public String getMyprop1() {
        return myprop1;
    }

    public void setMyprop1(String myprop1) {
        this.myprop1 = myprop1;
    }

    public String getMyprop2() {
        return myprop2;
    }

    public void setMyprop2(String myprop2) {
        this.myprop2 = myprop2;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "myprop1='" + myprop1 + '\'' +
                ", myprop2='" + myprop2 + '\'' +
                '}';
    }
}
