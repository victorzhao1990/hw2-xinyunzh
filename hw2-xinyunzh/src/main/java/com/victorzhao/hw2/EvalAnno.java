package com.victorzhao.hw2;

import java.util.HashSet;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import com.victorzhao.hw2.types.AbAnnoType;
import com.victorzhao.hw2.types.EvalAnnoType;
import com.victorzhao.hw2.types.Line;
import com.victorzhao.hw2.types.LingAnnoType;

public class EvalAnno extends JCasAnnotator_ImplBase {

	private static final double CONF_THRES = 0.3;

	private HashSet<String> hsAnno;

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
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		hsAnno = new HashSet<String>();
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation> lingIndex = aJCas
				.getAnnotationIndex(LingAnnoType.type);
		AnnotationIndex<Annotation> lineIndex = aJCas
				.getAnnotationIndex(Line.type);
		// System.out.println("*************");
		FSIterator<Annotation> lineIterator = lineIndex.iterator();
		String text = ((Line) lineIterator.get()).getContent();
		// String text = lineIndex.getContent();
		FSIterator<Annotation> lingIterator = lingIndex.iterator();
		while (lingIterator.hasNext()) {
			LingAnnoType lingInst = (LingAnnoType) lingIterator.get();
			if (lingInst.getConfidence() >= CONF_THRES) {
				EvalAnnoType eat = new EvalAnnoType(aJCas);
				eat.setId(lingInst.getId());
				eat.setCasProcessorId(lingInst.getCasProcessorId());
				eat.setContent(lingInst.getContent());
				int begin = removeSpace(text, lingInst.getBegin());
				int end = removeSpace(text, lingInst.getEnd());
				// System.out.println("*******************");
				String print = new String(lingInst.getId() + "|"
						+ lingInst.getBegin() + " " + lingInst.getEnd() + "|"
						+ lingInst.getContent());
				if (!hsAnno.contains(print.toString())) {
					hsAnno.add(print.toString());
					eat.setBegin(begin);
					eat.setEnd(end);
					eat.addToIndexes();
					// System.out.println(eat);
				}
				// System.out.println(eat);
			}
			lingIterator.moveToNext();
		}

		AnnotationIndex<Annotation> abIndex = aJCas
				.getAnnotationIndex(AbAnnoType.type);
		FSIterator<Annotation> abIterator = abIndex.iterator();
		while (abIterator.hasNext()) {
			AbAnnoType abInst = (AbAnnoType) abIterator.get();
			EvalAnnoType eat = new EvalAnnoType(aJCas);
			eat.setId(abInst.getId());
			eat.setCasProcessorId(abInst.getCasProcessorId());
			eat.setContent(abInst.getContent());
			int begin = removeSpace(text, abInst.getBegin());
			int end = removeSpace(text, abInst.getEnd());
			String print = new String(abInst.getId() + "|"
					+ abInst.getBegin() + " " + abInst.getEnd() + "|"
					+ abInst.getContent());
			if (!hsAnno.contains(print.toString())) {
				hsAnno.add(print.toString());
				eat.setBegin(begin);
				eat.setEnd(end);
				eat.addToIndexes();
			}
			abIterator.moveToNext();
			// System.out.println(eat);
		}
	}
}
