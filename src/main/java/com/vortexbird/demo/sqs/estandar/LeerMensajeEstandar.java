package com.vortexbird.demo.sqs.estandar;

import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.vortexbird.demo.sqs.credential.VortexAWSCredentials;

public class LeerMensajeEstandar {

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

			// Receive messages
			System.out.println("Recibiendo mensaje de :[" + qurl + "]");

			List<Message> messages = null;
			while ((messages = sqs.receiveMessage(new ReceiveMessageRequest(qurl)).getMessages()).size() > 0) {

				for (Message message : messages) {
					System.out.println("  Message");
					System.out.println("    MessageId:     " + message.getMessageId());
					System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
					System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
					System.out.println("    Body:          " + message.getBody());
					for (Entry<String, String> entry : message.getAttributes().entrySet()) {
						System.out.println("  Attribute");
						System.out.println("    Name:  " + entry.getKey());
						System.out.println("    Value: " + entry.getValue());
					}
					System.out.println();

					// Delete a message
					System.out.println("Deleting a message.\n");
					String messageRecieptHandle = message.getReceiptHandle();
					sqs.deleteMessage(new DeleteMessageRequest(qurl, messageRecieptHandle));
				}

			}
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
