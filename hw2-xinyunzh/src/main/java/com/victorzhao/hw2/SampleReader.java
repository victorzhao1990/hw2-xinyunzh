package com.victorzhao.hw2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
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
		try {
			sc = new Scanner(path);
		} catch (FileNotFoundException e) {
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
