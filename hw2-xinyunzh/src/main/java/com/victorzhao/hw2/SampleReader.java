package com.victorzhao.hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;

import com.victorzhao.hw2.types.Line;

public class SampleReader extends CollectionReader_ImplBase {
	/**
	 * Name of configuration parameter that must be set to the path of a input
	 * files.
	 */
	public static final String PARAM_INPUTPATH = "InputFile";

	// private BufferedReader br;
	private Scanner sc;

	/**
	 * @see org.apache.uima.collection.CollectionReader_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		File path = new File(
				((String) getConfigParameterValue(PARAM_INPUTPATH)).trim());
		
		// File path = new File("src/main/resources/data/sample.in");

		// if input directory does not exist or is not a directory, throw
		// exception
//		if (!path.exists() || !path.isDirectory()) {
//			throw new ResourceInitializationException(
//					ResourceConfigurationException.DIRECTORY_NOT_FOUND,
//					new Object[] { PARAM_INPUTPATH,
//							this.getMetaData().getName(), path.getPath() });
//		}
//		try {
//			br = new BufferedReader(new FileReader(path));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			sc = new Scanner(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see org.apache.uima.collection.CollectionReader#getNext(org.apache.uima.cas.CAS)
	 */
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}
		String line;
		line = sc.nextLine();
		//System.out.println(line);
		String[] sepeSent = line.split(" ", 2);
		Line lineAnno = new Line(jcas);
		lineAnno.setSentenceId(sepeSent[0]);
		lineAnno.setContent(sepeSent[1]); 
		lineAnno.addToIndexes();
	}

	// public void getNext(CAS aCAS) throws IOException, CollectionException {
	// JCas jcas;
	// try {
	// jcas = aCAS.getJCas();
	// } catch (CASException e) {
	// throw new CollectionException(e);
	// }
	//
	// // open input stream to file
	// File file = (File) mFiles.get(mCurrentIndex++);
	// String text = FileUtils.file2String(file, mEncoding);
	// // put document in CAS
	// jcas.setDocumentText(text);
	//
	// // set language if it was explicitly specified as a configuration
	// // parameter
	// if (mLanguage != null) {
	// jcas.setDocumentLanguage(mLanguage);
	// }
	//
	// // Also store location of source document in CAS. This information is
	// // critical
	// // if CAS Consumers will need to know where the original document
	// // contents are located.
	// // For example, the Semantic Search CAS Indexer writes this information
	// // into the
	// // search index that it creates, which allows applications that use the
	// // search index to
	// // locate the documents that satisfy their semantic queries.
	// SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(
	// jcas);
	// srcDocInfo.setUri(file.getAbsoluteFile().toURL().toString());
	// srcDocInfo.setOffsetInSource(0);
	// srcDocInfo.setDocumentSize((int) file.length());
	// srcDocInfo.setLastSegment(mCurrentIndex == mFiles.size());
	// srcDocInfo.addToIndexes();
	// }

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		if (sc.hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		sc.close();

	}

}
