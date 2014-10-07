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

public class AbAnno extends JCasAnnotator_ImplBase {

	/** The tagger instance */
	private Tagger t;

	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		t = new Tagger();
	}

	@Override
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
