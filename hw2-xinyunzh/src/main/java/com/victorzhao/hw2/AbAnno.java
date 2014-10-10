package com.victorzhao.hw2;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import abner.Tagger;

import com.victorzhao.hw2.types.AbAnnoType;
import com.victorzhao.hw2.types.Line;

/**
 * This class is the one of the annotators that consists of the aggregate analysis engine
 * in this Collection Process Engine, which mainly extract the gene tokens by using Abner 
 * package. 
 * 
 * @author victorzhao
 *
 */

public class AbAnno extends JCasAnnotator_ImplBase {

	/** The tagger instance used by Abner */
	private Tagger t;
	
	/** 
	 * This method will initialize a tagger instance used by Abner
	 * @param aContext
	 * 			Provides access to services and resources managed by the framework. 
	 * 			This includes configuration parameters, logging, and access to external resources.
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		t = new Tagger();
	}

	/**
	 * process(JCas aJCas) is the main method of AbAnno, which utilize the Abner package to extract all of the 
	 * biological terms and stored them into annotation.
	 * 
	 * @param aJCas
	 * 			a JCAS that AberAnno should process.
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation> lineIndex = aJCas
				.getAnnotationIndex(Line.type);
		FSIterator<Annotation> lineIterator = lineIndex.iterator();
		while (lineIterator.hasNext()) {
			Line lineInst = (Line) lineIterator.get();
			String[][] ents = t.getEntities(lineInst.getContent());
			for (int i = 0; i < ents[0].length; i++) {
				AbAnnoType aat = new AbAnnoType(aJCas);
				aat.setId(lineInst.getSentenceId());
				aat.setCasProcessorId("Abner");
				aat.setTag(ents[1][i]);
				aat.setContent(ents[0][i]);
				int begin = lineInst.getContent().indexOf(ents[0][i]);
				int end = begin + ents[0][i].length();
				aat.setBegin(begin);
				aat.setEnd(end);
				aat.addToIndexes();
			}
			lineIterator.moveToNext();
		}
	}
}
