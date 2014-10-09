
/* First created by JCasGen Thu Oct 09 18:33:35 EDT 2014 */
package com.victorzhao.hw2.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Oct 09 18:33:35 EDT 2014
 * @generated */
public class Line_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Line_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Line_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Line(addr, Line_Type.this);
  			   Line_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Line(addr, Line_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Line.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.victorzhao.hw2.types.Line");
 
  /** @generated */
  final Feature casFeat_Content;
  /** @generated */
  final int     casFeatCode_Content;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getContent(int addr) {
        if (featOkTst && casFeat_Content == null)
      jcas.throwFeatMissing("Content", "com.victorzhao.hw2.types.Line");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Content);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContent(int addr, String v) {
        if (featOkTst && casFeat_Content == null)
      jcas.throwFeatMissing("Content", "com.victorzhao.hw2.types.Line");
    ll_cas.ll_setStringValue(addr, casFeatCode_Content, v);}
    
  
 
  /** @generated */
  final Feature casFeat_SentenceId;
  /** @generated */
  final int     casFeatCode_SentenceId;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSentenceId(int addr) {
        if (featOkTst && casFeat_SentenceId == null)
      jcas.throwFeatMissing("SentenceId", "com.victorzhao.hw2.types.Line");
    return ll_cas.ll_getStringValue(addr, casFeatCode_SentenceId);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceId(int addr, String v) {
        if (featOkTst && casFeat_SentenceId == null)
      jcas.throwFeatMissing("SentenceId", "com.victorzhao.hw2.types.Line");
    ll_cas.ll_setStringValue(addr, casFeatCode_SentenceId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Line_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Content = jcas.getRequiredFeatureDE(casType, "Content", "uima.cas.String", featOkTst);
    casFeatCode_Content  = (null == casFeat_Content) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Content).getCode();

 
    casFeat_SentenceId = jcas.getRequiredFeatureDE(casType, "SentenceId", "uima.cas.String", featOkTst);
    casFeatCode_SentenceId  = (null == casFeat_SentenceId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SentenceId).getCode();

  }
}



    