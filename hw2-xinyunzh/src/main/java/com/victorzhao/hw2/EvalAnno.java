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
/**
 * This class is the last annotator of the aggregate analysis engine, which is also the last part of the whole
 * annotation pipeline. This annotator will collect the annotation from the previous two annotators, LingAnno and
 * AbAnno. By choosing a specific threshold of confidence value, all of the annotations produced by LingAnno which have the
 * higher score will be taken into consideration. Otherwise, it will lookup the results from AbAnno to check if there are some
 * identical annotation, which can complement the lower confidence assigned by LingAnno.
 * 
 * @author victorzhao
 *
 */
public class EvalAnno extends JCasAnnotator_ImplBase {

	/** The threshold value of confidence */
	private static final double CONF_THRES = 0.3;
	
	/** The HashMap instance that used to eliminate the duplicated annotations produced by AbnerAnno */
	private HashMap<String, AbAnnoType> hsAbner;
	
	/** The helper function that use to remove the spaces between the each tokens */
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

	/**
	 * process(JCas aJCas) is the main method of EvalAnno, which collect all of the valuable annotation produce
	 * by AbnerAnno and LingAnno. Moreover, it also store the final result into a new type of annotation, which is 
	 * EvalAnnoType.
	 * 
	 * @param aJCas
	 *            a JCAS that EvalAnno should process.
	 */
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
