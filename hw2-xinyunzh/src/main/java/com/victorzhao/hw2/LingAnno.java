package com.victorzhao.hw2;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;
import com.victorzhao.hw2.types.Line;
import com.victorzhao.hw2.types.LingAnnoType;

public class LingAnno extends JCasAnnotator_ImplBase {

	/** The ConfidenceChunker instance */
	private ConfidenceChunker chunker;

	/** The number of maximum words within a gene name */
	private static final int MAX_N_BEST_CHUNKS = 8;

	/** The File instance for model */
	private File model;

	/**
	 * This method will initialize one instance for private variable chunker ,
	 * which is one of the components of Lingpipe. It also loads the Genetag
	 * Model into the annotator.
	 * 
	 * @param aContext
	 * 
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		try {
			chunker = (ConfidenceChunker) AbstractExternalizable.
					readObject(new File(getContext().getResourceFilePath("HmmChunker")));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ResourceAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	/**
	 * process(JCas aJCas) will call annoGene(JCas aJCas, String text) to
	 * process JCAS with annotation of noun/phrase, adding gene annotation to
	 * them.
	 * 
	 * @param aJCas
	 *            a JCAS that GeneAnnotator should process.
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation> lineIndex = aJCas
				.getAnnotationIndex(Line.type);
		FSIterator<Annotation> lineIterator = lineIndex.iterator();
		while (lineIterator.hasNext()) {
			Line st = (Line) lineIterator.get();
			List<LingAnnoType> geneList = annoGene(aJCas, st.getContent());
			for (LingAnnoType gene : geneList) {
				gene.setId(st.getSentenceId());
				gene.setCasProcessorId("Lingpipe");
				gene.addToIndexes();
			}
			lineIterator.moveToNext();
		}

	}

	/**
	 * annoGene(JCas aJCas, String text) will call an instance of Chunker to
	 * process sentences, and add annotation of gene. This method will return an
	 * object of List type.
	 * 
	 * @param aJCas
	 *            a JCAS that annoGene(JCas aJCas, String text) should process.
	 * @param text
	 *            The content of one specific noun/phrase which this method will
	 *            process.
	 * @return
	 */
	private List<LingAnnoType> annoGene(JCas aJCas, String text) {
		List<LingAnnoType> geneList = new LinkedList<LingAnnoType>();
		char[] cs = text.toCharArray();
		Iterator<Chunk> it = chunker.nBestChunks(cs, 0, cs.length, MAX_N_BEST_CHUNKS);
		while (it.hasNext()) {
			LingAnnoType gt = new LingAnnoType(aJCas);
			Chunk chunk = it.next();
			double conf = Math.pow(2.0, chunk.score());
			int start = chunk.start();
			int end = chunk.end();
			gt.setBegin(start);
			gt.setEnd(end);
			gt.setContent(text.substring(start, end));
			gt.setConfidence(conf);
			geneList.add(gt);
		}
		return geneList;
	}
}
