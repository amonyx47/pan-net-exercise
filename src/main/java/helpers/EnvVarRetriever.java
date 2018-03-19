package helpers;

import exceptions.VariableNotSetException;

public class EnvVarRetriever {

    public static String retrieve(String varName) throws VariableNotSetException {
        String envVar = System.getenv(varName);
        if(envVar == null){
            envVar = System.getProperty(varName);
            if(envVar == null){
                throw new VariableNotSetException("Variable " + varName + " not set!");
            }
        }
        return envVar;
    }
}
