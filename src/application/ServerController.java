package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerController {

    @FXML
    private TextArea mLog;

    @FXML
    private Button mStartButton;

    @FXML
    void StartButtonPress(ActionEvent event) {
    	new Thread( () -> {
    		try {
				ServerSocket mSocket = new ServerSocket(3000);
				mLog.appendText("Server started on port: 3000, listening for connections: ");
				Socket mClientSocket = mSocket.accept();
				DataInputStream mInput = new DataInputStream(mClientSocket.getInputStream());
				DataOutputStream mOutput = new DataOutputStream(mClientSocket.getOutputStream());
				mStartButton.setDisable(true);
				while(true)
				{
					int mNumber = mInput.readInt();
					mLog.appendText(System.lineSeparator());
					mLog.appendText("Received the number" + " " + mNumber + " " + "from the client!");
			   	 mOutput.writeBoolean(calcPrime(mNumber));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}).start();
    }
    
    public boolean calcPrime(int number)
    {
    	if (number <=1)
    	{
    		return false;
    	}
    	
    	for(int a = 2; a < number; a++)
    	{
    		if (number % a == 0)
    		{
    			return false;
    		}
    	}
    	
    	return true;
    }

}
