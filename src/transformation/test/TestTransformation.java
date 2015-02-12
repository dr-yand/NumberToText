package transformation.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import transformation.util.NumTextTransformException;
import transformation.util.NumTextTransformation;

public class TestTransformation {
	public static void main(String[] args){
		
		InputStream is = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		
		try {
			is = new TestTransformation().getClass().getClassLoader().getResourceAsStream("input.txt");			
			br = new BufferedReader(new InputStreamReader(is));
			
			fos = new FileOutputStream("output.txt");
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			String numStr = "";
			while((numStr=br.readLine())!=null){
				String s = NumTextTransformation.numberToText(numStr);
				System.out.println(s);
				bw.write(s+"\r\n");				
			}
			bw.flush();
			bw.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NumTextTransformException e) {
			e.printStackTrace();
		}  
		finally{  
		}
	}
}

