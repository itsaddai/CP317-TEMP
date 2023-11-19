package gradeGUI;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


//Employs Singleton Pattern
public class Holder {
	private static Holder h = new Holder();
	private String nameFileLoc;
	private String courseFileLoc;
	private String outputFolderPath;
	private String outputFileName;

	private Holder() {
		nameFileLoc = "";
		courseFileLoc = "";
		outputFolderPath = "";
		outputFileName = "";
	}
	public static Holder getHolder() {
		return h;
	}
	void setNameFile(String path) {
		nameFileLoc = path;
	}
	void setCourseFile(String path) {
		courseFileLoc = path;
	}
	void setOutputFolderPath(String path) {
		outputFolderPath = path;
	}
	void setOutputFileName(String name) {
		outputFileName = name;
	}
	String getNameFileLoc() {
		return nameFileLoc;
	}
	String getCourseFileLoc() {
		return courseFileLoc;
	}
	String getOutputFolderPath() {
		return outputFolderPath;
	}
	String getOutputFileName() {
		return outputFileName;
	}
	/*
	void clearCourse() {
		courses.clear();
	}
	void clearName() { 
		names.clear();
	}
	void addCourse(String key, String val) {
		// key is a combination of Student ID and Course Code, comma-separated
		// val is all other columns, comma-separated
		courses.put(key, val);
	}
	void addName(String key, String val) {
		// key is Student ID
		// val is Student Name 
		names.put(key, val);
	}
	HashMap<String, String> getCourse() {
		return courses;
	}
	HashMap<String, String> getName() {
		return names;
	}
	*/
	void generateOutput() {
		//Output is Student ID, Student Name, Course Code, and Final Grade
		//TODO: Put output into output string
		HashMap<String, String> names = new HashMap<String, String>();
		ArrayList<String> output = new ArrayList<String>();
		
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
			while (courseReader.hasNextLine()) {
				String line = courseReader.nextLine();
	        	String[] courseCols = line.split(", ");
	        	String outLine = ""; 
	        	outLine += (String) courseCols[0] + ", ";
	        	outLine += (String) names.get(courseCols[0]) + ", ";
	        	outLine += (String) courseCols[1] + ", ";
	        	outLine += ""+(Math.round(((0.2*Float.parseFloat(courseCols[2]))+
	        			(0.2*Float.parseFloat(courseCols[3]))+
	        			(0.2*Float.parseFloat(courseCols[4]))+
	        			(0.4*Float.parseFloat(courseCols[5])))*10) / 10);
	        	output.add(outLine);
			}
			courseReader.close();
			
			File outFile = new File(getOutputFolderPath(), getOutputFileName());
			if (outFile.createNewFile()) {
			    System.out.println("File created:" + outFile.getName() + " @ " + outFile.getAbsolutePath());
		    } else {
			    System.out.println("File already exists.");
			    return;
		    }
			FileWriter myWriter = new FileWriter(outFile);
		    for (String i: output) {
		    	myWriter.write(i+"\r\n");
		    }
		    myWriter.close();
			
	    } catch (IOException e) {
	    	System.out.println("Error occurred while reading files");
	    }
	}
}
