package com.vortexbird.demo.sqs.credential;

import com.amazonaws.auth.AWSCredentials;



/**
 * Carga las credenciales configuradas en 
 * @author dgomez
 *
 */
public class VortexAWSCredentials implements AWSCredentials{
	
	public final static String AWSAccessKeyId="";
	public final static String AWSSecretKey="";
	

	@Override
	public String getAWSAccessKeyId() {
		return AWSAccessKeyId;
	}

	@Override
	public String getAWSSecretKey() {
		return AWSSecretKey;
	}


}
