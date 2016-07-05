package org.ceeker.web.sbootm;

public class BuilderTest {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class Builder {
        private String name;
        private String address;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public BuilderTest build() {
            return new BuilderTest(this);
        }
    }
    
    

    private BuilderTest() {
        super();
    }

    private BuilderTest(Builder builder) {
        this.address = builder.address;
        this.name = builder.name;
    }

    public static void main(String[] args) {
        BuilderTest sjs=new BuilderTest();
        Builder bu=new Builder();
        BuilderTest test = new BuilderTest.Builder().name("zxl").address("hh").build();
        System.out.println(test.name);
    }

    private void getBuild() {
        BuilderTest test = new BuilderTest.Builder().name("zxl").address("hh").build();
        System.out.println(test.name);
    }
}
