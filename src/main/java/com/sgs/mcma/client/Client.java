package com.sgs.mcma.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;

public class Client
{

	String serveraddress;
	int port;

	public Client(String serveraddress, int port)
	{
		this.serveraddress = serveraddress;
		this.port = port;
	}

	public String PlayerJoined(String player) throws ClientException,
			IOException
	{
		return StringUtils.newStringUtf8(doPost("/PlayerJoined",
				StringUtils.getBytesUtf8(player)));
	}

	public String PlayerLeft(String player) throws ClientException,
			IOException
	{
		return StringUtils.newStringUtf8(doPost("/PlayerLeft", StringUtils.getBytesUtf8(player)));
	}

	private byte[] doPost(String urlPath, Object postData)
			throws ClientException
	{
		try
		{
			URL url = new URL("http", serveraddress, port, urlPath);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.connect();
			OutputStream requestBody = connection.getOutputStream();
			// Write request body to OutputStream ...
			ObjectOutput out = new ObjectOutputStream(requestBody);
			out.writeObject(postData);
			requestBody.close();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				InputStream responseBody = connection.getInputStream();
				// Read response body from InputStream ...
				byte[] bytes = IOUtils.toByteArray(responseBody);
				responseBody.close();
				connection.disconnect();
				return bytes;
			} else
			{
				connection.disconnect();
				throw new ClientException();
			}
		} catch (Exception e)
		{
			throw new ClientException(e);
		}
	}

	public static void main(String[] args)
	{
		Thread thread = new Thread(new Runnable()
		{

			public void run()
			{
				try
				{
					new Client("localhost", 39640).PlayerJoined("testplayer");
					Thread.sleep(10000);
					new Client("localhost", 39640).PlayerLeft("testplayer");
					// System.out.println(result);
				} catch (ClientException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
