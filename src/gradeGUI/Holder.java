package gradeGUI;

import java.nio.file.Paths;
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
		nameFileLoc = Paths.get("").toAbsolutePath().toString()+"\\NameFile.txt";
		courseFileLoc = Paths.get("").toAbsolutePath().toString()+"\\CourseFile.txt";
		outputFolderPath = Paths.get("").toAbsolutePath().toString();
		outputFileName = "Output.txt";
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
		outputFileName = name;
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
	
	public void generateOutput() {
		//Output is Student ID, Student Name, Course Code, and Final Grade
		HashMap<String, String> names = new HashMap<String, String>();
		ArrayList<String> output = new ArrayList<String>();
		
		try {
			File outFile = new File(getOutputFolderPath(), getOutputFileName());
			if (outFile.createNewFile()) {
			    System.out.println("File created:" + outFile.getName() + " @ " + outFile.getAbsolutePath());
		    } else {
			    System.out.println("File already exists.");
			    return;
		    }
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
	        	outLine += ""+((float) Math.round((float) ((0.2*Float.parseFloat(courseCols[2]))+
	        			(0.2*Float.parseFloat(courseCols[3]))+
	        			(0.2*Float.parseFloat(courseCols[4]))+
	        			(0.4*Float.parseFloat(courseCols[5])))*10))/10.0;
	        	output.add(outLine);
			}
			courseReader.close();
			
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
