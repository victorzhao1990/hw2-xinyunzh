package com.victorzhao.hw2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

import com.victorzhao.hw2.types.EvalAnnoType;

public class CasConsumer extends CasConsumer_ImplBase {
	private File mOutputPath;
	private File sampleOut;
	private static final String PARAM_OUTPUTPATH = "OutputFile";
	private static final String PARAM_SAMPLEOUTPATH = "SampleOutFile";
	private HashSet<String> hsSample;
	private int countOfHit = 0;
	private int testOutcomePositive = 0;
	private int conditionPositive = 0;
	private FileWriter out = null;
	private BufferedWriter output;

	@Override
	public void initialize() throws ResourceInitializationException {
		mOutputPath = new File(
				(String) getConfigParameterValue(PARAM_OUTPUTPATH));
		if (!mOutputPath.exists()) {
			try {
				mOutputPath.createNewFile();
			} catch (IOException e) {
				throw new ResourceInitializationException(e);
			}
		}
		sampleOut = new File(
				(String) getConfigParameterValue(PARAM_SAMPLEOUTPATH));
		BufferedReader br;
		hsSample = new HashSet<String>();
		try {
			br = new BufferedReader(new FileReader(sampleOut));
			String line;
			while ((line = br.readLine()) != null) {
				hsSample.add(line);
				conditionPositive++;
				// System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out = new FileWriter(mOutputPath, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output = new BufferedWriter(out);
		// System.out.println("*********************");
	}

	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}
		FSIterator<Annotation> geneIt = jcas.getAnnotationIndex(
				EvalAnnoType.type).iterator();
		try {
			while (geneIt.hasNext()) {
				testOutcomePositive++;
				EvalAnnoType gene = (EvalAnnoType) geneIt.next();
				String linePrint = new String(gene.getId() + "|"
						+ gene.getBegin() + " " + gene.getEnd() + "|"
						+ gene.getContent());
				// System.out.println(linePrint);
				output.write(linePrint + "\n");
				if (hsSample.contains(linePrint.toString())) {
					countOfHit++;
				}
			}
		} catch (IOException e) {
			throw new ResourceProcessException(e);
		}
	}

	public void collectionProcessComplete(ProcessTrace arg0) throws ResourceProcessException, IOException {
		output.close();
		// Evaluation Part
		
		System.out.println("Number of Hits is" + " " + countOfHit);
		double precision = 1.0 * countOfHit / testOutcomePositive;
		double recall = 1.0 * countOfHit / conditionPositive;
		System.out.println("Precision is " + precision);
		System.out.println("Recall is " + recall);
		System.out.println("F-measure is " + 2 * precision * recall
				/ (precision + recall));
	    System.out.println("*************");

	}
}
