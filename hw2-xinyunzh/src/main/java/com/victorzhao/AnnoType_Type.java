
/* First created by JCasGen Thu Oct 09 17:12:00 EDT 2014 */
package com.victorzhao;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import edu.cmu.deiis.types.Annotation_Type;

/** The base type for all types of annotators.
 * Updated by JCasGen Thu Oct 09 17:13:44 EDT 2014
 * @generated */
public class AnnoType_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnnoType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnnoType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnnoType(addr, AnnoType_Type.this);
  			   AnnoType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnnoType(addr, AnnoType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AnnoType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.victorzhao.AnnoType");
 
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
      jcas.throwFeatMissing("Content", "com.victorzhao.AnnoType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Content);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContent(int addr, String v) {
        if (featOkTst && casFeat_Content == null)
      jcas.throwFeatMissing("Content", "com.victorzhao.AnnoType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Content, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Id;
  /** @generated */
  final int     casFeatCode_Id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_Id == null)
      jcas.throwFeatMissing("Id", "com.victorzhao.AnnoType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_Id == null)
      jcas.throwFeatMissing("Id", "com.victorzhao.AnnoType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Id, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public AnnoType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Content = jcas.getRequiredFeatureDE(casType, "Content", "uima.cas.String", featOkTst);
    casFeatCode_Content  = (null == casFeat_Content) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Content).getCode();

 
    casFeat_Id = jcas.getRequiredFeatureDE(casType, "Id", "uima.cas.String", featOkTst);
    casFeatCode_Id  = (null == casFeat_Id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Id).getCode();

  }
}



    