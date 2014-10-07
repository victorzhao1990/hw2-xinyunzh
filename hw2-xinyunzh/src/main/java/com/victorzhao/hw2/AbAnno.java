package com.victorzhao.hw2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import abner.Tagger;

import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;
import com.victorzhao.hw2.types.AbAnnoType;
import com.victorzhao.hw2.types.Line;
import com.victorzhao.hw2.types.LingAnnoType;

public class AbAnno extends JCasAnnotator_ImplBase {
	
	/** The tagger instance */
	private Tagger t;
	private static final double CONF_THRES = 0.3;
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		t = new Tagger();
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation> lingIndex = aJCas
				.getAnnotationIndex(LingAnnoType.type);
		FSIterator<Annotation> lingIterator = lingIndex.iterator();
		while (lingIterator.hasNext()) {
			LingAnnoType lingInst = (LingAnnoType) lingIterator.get();
			if (lingInst.getConfidence() < CONF_THRES) {
				String[][] ents = t.getEntities(lingInst.getContent());
				// Test for print the result by Abner
//				for (int i=0; i<ents[0].length; i++) {
//				    System.out.println(ents[1][i]+"\t["+ents[0][i]+"]");
//				}
				
				for (int i = 0; i < ents[0].length; i++) {
					AbAnnoType aat = new AbAnnoType(aJCas);
					aat.setId(lingInst.getId());
					aat.setCasProcessorId("Abner");
					aat.setTag(ents[1][i]);
					// System.out.println(ents[1][i]);
					aat.setContent(ents[0][i]);
					int begin = lingInst.getContent().indexOf(ents[1][i]);
					int end = begin + ents[0][i].length();
					aat.setBegin(begin);
					aat.setEnd(end);
					aat.addToIndexes();
				}
			}
			lingIterator.moveToNext();
		}
	}
}
