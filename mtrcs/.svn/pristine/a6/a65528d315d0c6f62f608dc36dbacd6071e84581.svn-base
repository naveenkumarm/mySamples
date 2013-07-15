/**
 * 
 */
package com.epro.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.AbstractView;

import com.csvreader.CsvWriter;

/**
 * Custom view implementation to export content to CSV using JavaCSV API.
 * 
 * @author gouruv
 */
public class CsvView extends AbstractView {
	
	/** Default file type */
	private static final String CONTENT_TYPE = "text/csv";
	/** Logger */
	private static final Logger log = Logger.getLogger(CsvView.class);
	/** Default CSV delimiter */
	public static final char CHAR_CSV_DELIMITER = ',';
	/** Default model key */
	public static final String JAVA_CSV_MODEL_KEY = "csvList";
	/** Default file name model key */
	public static final String FILENAME_KEY = "filename";
	
	public CsvView() {
		setContentType(CONTENT_TYPE);
	}
	
	@SuppressWarnings("unchecked")
	public void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Entered renderMergedOutputModel() method.");
		
		response.setContentType(getContentType());
		String fileName = (String)model.get(FILENAME_KEY);
		if(fileName != null && !fileName.equals("")){
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		}else {
			response.setHeader("Content-Disposition", "attachment;filename=ALRDashboardResults.csv");
		}	
		convertAndWrite(model, response.getWriter());
	}
	
	public void convertAndWrite(Map model, Writer writer) throws IOException {
	
		List<String[]> csvList = (List<String[]>)model.get(JAVA_CSV_MODEL_KEY);
		CsvWriter csvWriter = new CsvWriter(writer, CHAR_CSV_DELIMITER);
		for(String[] recordArray : csvList) {
			csvWriter.writeRecord(recordArray);
		}
		writer.flush();
		csvWriter.close();
	}
}
