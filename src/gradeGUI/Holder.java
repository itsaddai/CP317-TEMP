package gradeGUI;


public class Holder {
	String nameFileLoc;
	String courseFileLoc;
	String outputFolderPath;
	String outputFileName;
	
	public Holder() {
		nameFileLoc = "";
		courseFileLoc = "";
		outputFolderPath = "";
		outputFileName = "";
	}

	void setNameFile(String path) {
		//TODO: Verify path
		nameFileLoc = path;
	}
	void setCourseFile(String path) {
		//TODO: Verify path
		courseFileLoc = path;
	}
	void setOutputFolderPath(String path) {
		//TODO: Verify path
		outputFolderPath = path;
	}
	void setOutputFileName(String name) {
		//TODO: Verify path
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
}
