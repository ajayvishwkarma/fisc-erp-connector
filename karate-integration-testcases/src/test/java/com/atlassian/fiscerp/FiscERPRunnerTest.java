package com.atlassian.fiscerp;

import com.intuit.karate.junit5.Karate;

class FiscERPRunnerTest {
    
    @Karate.Test
    Karate fiscERPService() {
        return Karate.run().relativeTo(getClass());
    }    

}