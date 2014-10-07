package com.victorzhao.hw2;

import java.util.HashMap;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import com.victorzhao.hw2.types.AbAnnoType;
import com.victorzhao.hw2.types.EvalAnnoType;
import com.victorzhao.hw2.types.Line;
import com.victorzhao.hw2.types.LingAnnoType;

public class EvalAnno extends JCasAnnotator_ImplBase {

	private static final double CONF_THRES = 0.1;

	private HashMap<String, AbAnnoType> hsAbner;

	private int removeSpace(String text, int pos) {
		int posWithoutSpace = 0, index = 0;
		while (index < pos) {
			if (text.charAt(index) != ' ') {
				posWithoutSpace++;
			}
			index++;
		}
		return posWithoutSpace;
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		hsAbner = new HashMap<String, AbAnnoType>();

		AnnotationIndex<Annotation> abIndex = aJCas
				.getAnnotationIndex(AbAnnoType.type);
		FSIterator<Annotation> abIterator = abIndex.iterator();
		while (abIterator.hasNext()) {
			AbAnnoType abInst = (AbAnnoType) abIterator.get();
			hsAbner.put(abInst.getContent(), abInst);
			abIterator.moveToNext();
		}

		AnnotationIndex<Annotation> lingIndex = aJCas
				.getAnnotationIndex(LingAnnoType.type);
		AnnotationIndex<Annotation> lineIndex = aJCas
				.getAnnotationIndex(Line.type);
		FSIterator<Annotation> lineIterator = lineIndex.iterator();
		String text = ((Line) lineIterator.get()).getContent();
		FSIterator<Annotation> lingIterator = lingIndex.iterator();

		while (lingIterator.hasNext()) {
			LingAnnoType lingInst = (LingAnnoType) lingIterator.get();
			if (lingInst.getConfidence() >= CONF_THRES) {
				EvalAnnoType eat = new EvalAnnoType(aJCas);
				eat.setId(lingInst.getId());
				eat.setCasProcessorId(lingInst.getCasProcessorId());
				eat.setContent(lingInst.getContent());
				int begin = removeSpace(text, lingInst.getBegin());
				int end = removeSpace(text, lingInst.getEnd() - 1);
				eat.setBegin(begin);
				eat.setEnd(end);
				eat.addToIndexes();
			} else if (hsAbner.containsKey(lingInst.getContent())) {
				AbAnnoType abInst = hsAbner.get(lingInst.getContent());
				EvalAnnoType eat = new EvalAnnoType(aJCas);
				eat.setId(abInst.getId());
				eat.setCasProcessorId(abInst.getCasProcessorId());
				eat.setContent(abInst.getContent());
				int begin = removeSpace(text, abInst.getBegin());
				int end = removeSpace(text, abInst.getEnd() - 1);
				eat.setBegin(begin);
				eat.setEnd(end);
				eat.addToIndexes();
			}
			lingIterator.moveToNext();
		}

	}
}
