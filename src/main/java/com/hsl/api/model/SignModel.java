package com.hsl.api.model;

public class SignModel {
    private String generatorStr;
    private String generatorSig;

    public SignModel() {
    }

    public String getGeneratorStr() {
        return this.generatorStr;
    }

    public String getGeneratorSig() {
        return this.generatorSig;
    }

    public void setGeneratorStr(String generatorStr) {
        this.generatorStr = generatorStr;
    }

    public void setGeneratorSig(String generatorSig) {
        this.generatorSig = generatorSig;
    }
}
