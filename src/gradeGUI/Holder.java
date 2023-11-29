package gradeGUI;

import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


//Employs Singleton Pattern
public class Holder {
	private static Holder h = new Holder();
	private String nameFileLoc;
	private String courseFileLoc;
	private String outputFolderPath;
	private String outputFileName;
	private String messageStr;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Holder() {
		nameFileLoc = Paths.get("").toAbsolutePath().toString()+"\\NameFile.txt";
		courseFileLoc = Paths.get("").toAbsolutePath().toString()+"\\CourseFile.txt";
		outputFolderPath = Paths.get("").toAbsolutePath().toString();
		outputFileName = "Output.txt";
		messageStr = "";
	}
	public static Holder getHolder() {
		return h;
	}
	public void setNameFile(String path) {
		nameFileLoc = path;
	}
	public void setCourseFile(String path) {
		courseFileLoc = path;
	}
	public void setOutputFolderPath(String path) {
		outputFolderPath = path;
	}
	public void setOutputFileName(String name) {
		outputFileName = name + ".txt";
	}
	public void addMessage(String message) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		messageStr = messageStr + "\r\n@ " + dateFormat.format(timestamp) + ": " + message;
	}
	public void addError(String message) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		messageStr = messageStr + "\r\nERROR @ " + dateFormat.format(timestamp) + ": " + message;
	}
	public void clearMessageStr() {
		messageStr = "";
	}
	public String getNameFileLoc() {
		return nameFileLoc;
	}
	public String getCourseFileLoc() {
		return courseFileLoc;
	}
	public String getOutputFolderPath() {
		return outputFolderPath;
	}
	public String getOutputFileName() {
		return outputFileName;
	}
	public String getMessageStr() {
		return messageStr;
	}
	public void generateOutput() {
		//Output is Student ID, Student Name, Course Code, and Final Grade
		HashMap<String, String> names = new HashMap<String, String>();
		ArrayList<String> output = new ArrayList<String>();
		clearMessageStr();
		try {
			File nameObj = new File(getNameFileLoc());
			Scanner nameReader = new Scanner(nameObj);
			while (nameReader.hasNextLine()) {
				String line = nameReader.nextLine();
	        	String[] nameCols = line.split(", ");
	        	names.put(nameCols[0], nameCols[1]);
			}
			nameReader.close();
			File courseObj = new File(getCourseFileLoc());
			Scanner courseReader = new Scanner(courseObj);
			int courseLine = 0;
			while (courseReader.hasNextLine()) {
				courseLine++;
				String line = courseReader.nextLine();
	        	String[] courseCols = line.split(", ");
	        	//TODO: Fix this so it throws exception when there are more than 6 columns in CourseFile.txt
	        	if (courseCols.length != 6) {
	        		courseReader.close();
	        		throw new Exception("Six columns not found on line " + Integer.toString(courseLine));
	        	}
	        	String outLine = ""; 
	        	outLine += (String) courseCols[0] + ", ";
	        	outLine += (String) names.get(courseCols[0]) + ", ";
	        	outLine += (String) courseCols[1] + ", ";
	        	if (Float.parseFloat(courseCols[2]) < 0.0 || Float.parseFloat(courseCols[2]) > 100.0 ||
	        			(Float.parseFloat(courseCols[3]) < 0.0 || Float.parseFloat(courseCols[3]) > 100.0) ||
	        			(Float.parseFloat(courseCols[4]) < 0.0 || Float.parseFloat(courseCols[4]) > 100.0) ||
	        			(Float.parseFloat(courseCols[5]) < 0.0 || Float.parseFloat(courseCols[5]) > 100.0)) {
	        		courseReader.close();
	        		throw new Exception("Grade out of bounds");
	        	}
	        	outLine += ""+((float) Math.round((float) ((0.2*Float.parseFloat(courseCols[2]))+
	        			(0.2*Float.parseFloat(courseCols[3]))+
	        			(0.2*Float.parseFloat(courseCols[4]))+
	        			(0.4*Float.parseFloat(courseCols[5])))*10))/10.0;
	        	output.add(outLine);
			}
			courseReader.close();
			
			File outFile = new File(getOutputFolderPath(), getOutputFileName());
			if (outFile.createNewFile()) {
			    System.out.println("File created: " + outFile.getAbsolutePath());
			    addMessage("File created: " + outFile.getAbsolutePath());
		    } else {
			    System.out.println("File already exists.");
			    addError("File already exists.");
			    return;
		    }
			
			FileWriter myWriter = new FileWriter(outFile);
		    for (String i: output) {
		    	myWriter.write(i+"\r\n");
		    }
		    myWriter.close();
			
	    } catch (IOException e) {
	    	System.out.println(e);
	    	addError(e.getMessage());
	    } catch (NumberFormatException e) {
	    	System.out.println(e);
	    	addError("Non-numerical value found with " + e.getMessage());
	    } catch (Exception e) {
	    	System.out.println(e);
	    	addError(e.getMessage());
	    }
	}
}
