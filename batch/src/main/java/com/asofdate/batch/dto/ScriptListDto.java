package com.asofdate.batch.dto;

/**
 * Created by hzwy23 on 2017/7/13.
 */
public class ScriptListDto {
    @Deprecated
    public String basePath;
    public String scriptName;
    public String relativePath;
    public String parentPath;
    public String scriptType;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    @Override
    public String toString() {
        return "ScriptListDto{" +
                "basePath='" + basePath + '\'' +
                ", scriptName='" + scriptName + '\'' +
                ", relativePath='" + relativePath + '\'' +
                ", parentPath='" + parentPath + '\'' +
                ", scriptType='" + scriptType + '\'' +
                '}';
    }
}
