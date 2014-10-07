

/* First created by JCasGen Sun Oct 05 18:33:07 EDT 2014 */
package com.victorzhao.hw2.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import edu.cmu.deiis.types.Annotation;


/** 
 * Updated by JCasGen Sun Oct 05 21:27:58 EDT 2014
 * XML source: /Users/victorzhao/git/hw2-xinyunzh/hw2-xinyunzh/src/main/resources/descriptors/hw2TypeSystemDescriptor.xml
 * @generated */
public class LingAnnoType extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(LingAnnoType.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected LingAnnoType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public LingAnnoType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public LingAnnoType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public LingAnnoType(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Content

  /** getter for Content - gets 
   * @generated
   * @return value of the feature 
   */
  public String getContent() {
    if (LingAnnoType_Type.featOkTst && ((LingAnnoType_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "com.victorzhao.hw2.types.LingAnnoType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LingAnnoType_Type)jcasType).casFeatCode_Content);}
    
  /** setter for Content - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContent(String v) {
    if (LingAnnoType_Type.featOkTst && ((LingAnnoType_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "com.victorzhao.hw2.types.LingAnnoType");
    jcasType.ll_cas.ll_setStringValue(addr, ((LingAnnoType_Type)jcasType).casFeatCode_Content, v);}    
   
    
  //*--------------*
  //* Feature: Id

  /** getter for Id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (LingAnnoType_Type.featOkTst && ((LingAnnoType_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "com.victorzhao.hw2.types.LingAnnoType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((LingAnnoType_Type)jcasType).casFeatCode_Id);}
    
  /** setter for Id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (LingAnnoType_Type.featOkTst && ((LingAnnoType_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "com.victorzhao.hw2.types.LingAnnoType");
    jcasType.ll_cas.ll_setStringValue(addr, ((LingAnnoType_Type)jcasType).casFeatCode_Id, v);}    
  }

    