package com.vortexbird.demo.sqs.estandar;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.vortexbird.demo.sqs.credential.VortexAWSCredentials;

public class EnviarMensajeEstandar {

	private final static String qurl = "https://sqs.us-east-1.amazonaws.com/109822741979/cola-estandar";

	private static BasicAWSCredentials credentials = null;

	private static void init() throws Exception {
		credentials = new BasicAWSCredentials(VortexAWSCredentials.AWSAccessKeyId, VortexAWSCredentials.AWSSecretKey);
	}

	public static void main(String[] args) throws Exception {
		init();

		AmazonSQS sqs = new AmazonSQSClient(credentials);

		System.out.println("===========================================");
		System.out.println("Iniciando con Amazon SQS");
		System.out.println("===========================================\n");

		try {
			// Send a message
			for (int i = 0; i < 100; i++) {
				System.out.println("Enviando mensaje a la cola:[" + qurl + "]");
				sqs.sendMessage(new SendMessageRequest(qurl, "Este es un mensaje de texto:[" + i + "]"));
			}

			sqs.shutdown();

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon SQS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with SQS, such as not "
					+ "being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}

}
