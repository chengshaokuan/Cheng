/**
 * MobileCodeWSCallbackHandler.java
 *
 * This log was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package com.WebService.Axis2Client;


/**
 *  MobileCodeWSCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class MobileCodeWSCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public MobileCodeWSCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public MobileCodeWSCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the clientTest data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for getMobileCodeInfo method
     * override this method for handling normal response from getMobileCodeInfo operation
     */
    public void receiveResultgetMobileCodeInfo(
        com.WebService.Axis2Client.MobileCodeWSStub.GetMobileCodeInfoResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMobileCodeInfo operation
     */
    public void receiveErrorgetMobileCodeInfo(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getDatabaseInfo method
     * override this method for handling normal response from getDatabaseInfo operation
     */
    public void receiveResultgetDatabaseInfo(
        com.WebService.Axis2Client.MobileCodeWSStub.GetDatabaseInfoResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getDatabaseInfo operation
     */
    public void receiveErrorgetDatabaseInfo(java.lang.Exception e) {
    }
}
