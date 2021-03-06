
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

/** 
 * Updated by JCasGen Thu Oct 09 18:33:35 EDT 2014
 * @generated */
public class AbAnnoType_Type extends AnnoType_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AbAnnoType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AbAnnoType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AbAnnoType(addr, AbAnnoType_Type.this);
  			   AbAnnoType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AbAnnoType(addr, AbAnnoType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AbAnnoType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.victorzhao.hw2.types.AbAnnoType");
 
  /** @generated */
  final Feature casFeat_Tag;
  /** @generated */
  final int     casFeatCode_Tag;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTag(int addr) {
        if (featOkTst && casFeat_Tag == null)
      jcas.throwFeatMissing("Tag", "com.victorzhao.hw2.types.AbAnnoType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Tag);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTag(int addr, String v) {
        if (featOkTst && casFeat_Tag == null)
      jcas.throwFeatMissing("Tag", "com.victorzhao.hw2.types.AbAnnoType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Tag, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public AbAnnoType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Tag = jcas.getRequiredFeatureDE(casType, "Tag", "uima.cas.String", featOkTst);
    casFeatCode_Tag  = (null == casFeat_Tag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Tag).getCode();

  }
}



    